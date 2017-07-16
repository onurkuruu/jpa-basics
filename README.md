# JavaEE JPA

İçindekiler

- [JPA Mimarisi](#jpa-mimarisi)
- [Entity Inheritance Strategies](#entity-inheritance-strategies)
	- [Single Table Strategy](#single-table-strategy)
	- [Joined Table Strategy](#joined-table-strategy)
	- [Table Per Class Strategy](#table-per-class-strategy)
- [Entity Relationships](#entity-relationships)
	- [OneToOne Relation](#onetoone-relation)
	- [OneToMany-ManyToOne Relation](#onetomany-manytoone-relation)
	- [ManyToMany Relation](#manytomany-relation)
- [JPA Criteria API](#jpa-criteria-api)
- [JPA Notasyonları](#jpa-notasyonları)
  - [Sınıflar için geliştirilmiş notasyonlar](#sınıflar-icin-gelistirilmis-notasyonlar)
  - [İlişkiler için geliştirilmiş notasyonlar](#iliskiler-icin-gelistirilmis-notasyonlar)
  - [Değer üretme için geliştirilmiş notasyonlar](#deger-uretme-icin-gelistiriilmis-notasyonlar)
  - [Callback metotları için geliştirilmiş notasyonlar](#callback-metodlari-icin-gelistirilmis-notasyonlar)
  - [JPQL Sorguları içn geliştirilmiş notastonlar](#jpql-sorgulari-icin-gelistirilmis-notasyonlar)
  - [JAVA EE için geliştirilmiş notasyonlar](#java-ee-icin-gelistirilmis-notasyonlar)
  - [Mapping işlemleri için geliştirilmiş notasyonlar](#mapping-islemleri-icin-gelistirilmis-notasyonlar)

JPA(Java Persistence API), javanın ORM standardıdır. Java SE ve EE ortamlarını destekler.

JPA’dan önce Java EE 1 ile birlikte gelen Entity Beanler kullanılmaktaydı fakat bu yaklaşım fazla akademik olması ve kullanım zorluğu nedeniyle fazla ilgi görmemiştir.

Java EE 2 ile birlikte performans açısından düzeltmeler yapılsa da Entity Beanlere güven sarsılmıştı ve ORM aracı ihtiyacı vardı. Bu arada bağımsız geliştiriciler kendi yaklaşımlarıyla bu eksikliği gidermeye çalışmaktaydı ve geliştirilen bazı ORM araçları çok beğenildi.

Java EE 5’ de EJB 3 ile gelen bir özelliktir. Bağımsız ORM araçlarının(Hibernate, Toplink vs.) güçlü yönlerini alarak standartlar oluşturulup JPA yayınlanmıştır. Bağımsız ORM araçlarının da ilgisini çeken bu standart kısa süre içerisinde ORM araçları tarafından uygulanıp ürünler piyasaya sürülmüştür. Java EE 6 ile JPA’nın 2. sürümü yayınlanmış ve daha güçlü hale gelmiştir.

### JPA Mimarisi

Yazdığımız POJO sınıfları ve veri tabanı tabloları bu mimari sayesinde eşleştirilir. JPA mimarisinde ki çekirdek sınıf ve interfacelerden biraz bahsedecek olursak:

* EntityManagerFactory: EntityManager sınıf nesnelerinin üretilmesinden ve yönetilmesinden sorumlu olan sınıftır.
* EntityManager: Bir interfacedir ve kalıcılık işlemlerini yönetir. Query sınıfının factorysi gibi düşünebiliriz.
* Entity: Kalıcığı sağlanacak sınıflardır, veri tabanında tablolara karşılık gelir.
* EntityTransaction: Kalıcılık işlemlerinin transactionlarını yönetir. Her EntityManager için bir adet EntityTransaction vardır.
* Persistence: Bu sınıf EntityManagerFactory oluşturmak için static metotlar içerir.
* Query: Bir interfacedir. Entity sınıflarının veri tabanı işlemleri için JPA sağlayacıları bu arayüzü implemente eder.

### Entity Inheritance Strategies

#### Single Table Strategy

Tüm sınıf alanlarını tek tabloda toplayan stratejidir. Tablo tiplerini ayırt etmek için ayrı bir alan kullanılır.

Süper sınıfımızı oluşturalım:

```java
@Entity
@Table(name = "ANIMAL")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ANIMAL_TYPE")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANIMAL_ID")
    private Integer animalId;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "NUMBER_OF_LEGS")
    private Integer numberOfLegs;

    @Column(name = "VEGETARIAN")
    private Boolean vegetarian;
}
```
Inheritance ile kalıtım stratejimizi belirtiyoruz. DiscriminatorColumn notasyonu ile kalıtım alan sınıfları ayırt edebilmek için veri tabanında oluşturulan alanın adını belirtiyoruz.

```java
@Entity
@DiscriminatorValue(value = "CAT")
public class Cat extends Animal {

    @Column(name = "HUNTER")
    private Boolean hunter;
}
```
DiscriminatorValue, DiscriminatorColumn ile belirtilen alan adının tablo için karşılığının ne olacağını belirtiyoruz. Bu işlemlerden sonra yeni bir Cat nesnesi veri tabanına kayıt edildiğinde animalId, owner, numberOfLegs, vegetarian, hunter alanlarının yanı sıra animal_type alanı cat olan bir satır oluşturulacaktır.

[Single Table Strategy Mapping kodları](https://github.com/onurkuruu/jpa-basics/tree/master/src/main/java/com/oonurkuru/jpa/domains/Inheritance/SingleTableStrategy) ve [Kodları Çalıştırmak İçin](https://github.com/onurkuruu/jpa-basics/blob/master/src/main/java/com/oonurkuru/jpa/services/SingleTableExample.java)

#### Joined Table Strategy

Bu strateji ile veri tabanında entity sınıfı kadar tablo oluşturulur fakat hepsinin primary key alanları süper sınıf tarafından sağlanmaktadır.

```java

@Entity
@Table(name = "J_T_ANIMAL")
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANIMAL_ID")
    private Integer animalId;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "NUMBER_OF_LEGS")
    private Integer numberOfLegs;

    @Column(name = "VEGETARIAN")
    private Boolean vegetarian;

...
}

@Entity
@Table(name = "J_T_CAT")
@PrimaryKeyJoinColumn(referencedColumnName = "ANIMAL_ID")
public class Cat extends Animal {

    @Column(name = "HUNTER")
    private Boolean hunter;
...
}
```
Kalıtım stratejisi Joined olarak belirlendi, PrimaryKeyJoinColumn notasyonu ile Animal sınıfının primary key alanı belirtildi. Bu sayede Cat tablosuna girilen her kayıt Animal sınıfından bir kayıt ile eşleştirilebilecek.

[Joined Table Strategy Mapping Kodları]() ve [Kodları çalıştırmak için]()

#### Table Per Class Strategy

Bu stratejide de her sınıf için bir tablo oluşturulur fakat süper sınıfın tablo kayıtları yoktur. Alt sınıflar için kalıtım ile gelen özelliklerde dahil edilerek tablo sütunları oluşur. Alt sınıflar primary key oluşturma yöntemini süper sınıftan alır. Primary Key oluşturma strajesi olarak IDENTITY kullanılamaz.

```java
@Entity
@Table(name = "T_P_ANIMAL")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ANIMAL_ID")
    private Integer animalId;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "NUMBER_OF_LEGS")
    private Integer numberOfLegs;

    @Column(name = "VEGETARIAN")
    private Boolean vegetarian;
}

@Entity
@Table(name = "T_P_CAT")
public class Cat extends Animal {

    @Column(name = "HUNTER")
    private Boolean hunter;
}
```

[Table Per Class Mapping Kodları]() ve [Kodları çalıştırmak için]()
### Entity Relationships

Sınıflar arasında ilişkiler tanımlamak için 4 adet notasyon vardır. Bunları açıklamaya geçmeden önce bilinmesi gereken birkaç şey;

* fetch: İlişkili veriler 2 yöntem ile veri tabanından alınabilir. Eager ve Lazy. Veri tabanında bir sorgu yaptığımızda ilişkili alanda beraberinde geliyorsa bu eager fetch olarak bilinir. Veri tabanından sorgu yaptığımızda ilişkili alan sadece çağrıldığında geliyorsa bu lazy olarak bilinir. Örneğin üniversite nesnemize ait temel özellikler ve bunun yanı sıra öğrenciler isminde OneToMany olarak tanımlanmış bir ilişki var. Eager yöntemiyle temel alanların yanı sıra öğrencilerde getirilir, lazy yöntemiyle getOgrenciler() metotu çalıştığında öğrenciler getirilir.

* cascade: İlişkili veriler ile senkron işlemler yapılmak istendiğinde kullanılır. Bir entity nesnesi silindiğinde, güncellendiğinde vs. ilişkili olan entityninde bu işlemlerden geçmesini istiyorsak cascade notasyonunu kullanırız. Detach, merge, persist, refresh, remove ya da all olarak seçilebilir.

* orphanRemoval: İlişkili olan verinin boşaltılması durumunda bu durum otomatik olarak veri tabanına yansıtılır. Örneğin üniversite nesnemiz öğrenciler ile ilişkili 1-N şeklinde, öğrenciler bir liste olarak tutuluyor. setOgrenciler(null) yaptığımız anda bu değişiklik veri tabanına yansır.

#### OneToOne Relation

Bu ilişki türünde bir nesne ile sadece bir nesne eşleşir. İlişkiler tek yönlü ya da iki yönlü olabilir. İlişkiler iki yönlü olursa ilişkinin sahibine karar verilmeli ve diğer tarafa mappedBy özelliği uygulanmalıdır.

```java
@Entity
@Table(name = "O_T_O_PERSON")
public class Person {

…
    @OneToOne
    @JoinColumn(name = "TOOTHBRUSH")
    private Toothbrush toothbrush;
…
}

@Entity
@Table(name = "O_T_O_TOOTHBRUSH")
public class Toothbrush {
…
@OneToOne(mappedBy = "toothbrush", fetch = FetchType.LAZY)
private Person person;
…
}
```
İlişkimizi 2 yönlü tanımladık ve ilişkinin sahibi olarak Person tarafını seçtik. JoinColumn notasyonu ile foreign key alanının ismini ve bu alanın Person tablosunda olacağını belirttik. İlişkimizin pasif tarafı olan Tootbrush tarafına mappedBy özelliğini ekledik.

#### OneToMany-ManyToOne Relation

1-N ve N-1 ilişkiler tanımlamamıza yardımcı olur. İlişkimizi 2 taraflı kuracaksak bir taraf OneToMany diğer taraf ManyToOne olmalıdır. 2 yönlü ilişkilerde OneToMany tarafı direkt olarak pasif taraf olarak belirlenir. ManyToOne olan tarafa JoinColumn eklenerek foreign key alanının burada tutulacağını belirtiriz. 

OneToMany ilişkisinin tek yönlü olması durumunda 3. bir ara tablo oluşarak iki sınıf arasındaki bağlantılı alanları tutar.

ManyToOne ilişkisinin tek yönlü olası durumunda ekstra bir olay meydana gelmez. Yine 2 tablo oluşur ve JoinColumn notasyonundan dolayı foreign key alanı ManyToOne yazılan tabloda tutulur.


```java
@Entity
@Table(name = "O_T_M_STUDENT")
public class Student {

@ManyToOne
@JoinColumn(name = "SCHOOL")
private School school;

...
}

@Entity
@Table(name = "O_T_M_SCHOOL")
public class School {

@OneToMany(mappedBy = "school")
private List<Student> studentList;
...
}
```

#### ManyToMany Relation

Bir sınıfın bir ya da daha fazla nesnesinin diğer sınıfta bir ya da birden fazla nesne ile ilişkili olması durumudur. Bu ilişkinin sağlanabilmesi için 3. tablo gereksinimi vardır. Oluşan ara tablo 2 sınıfın primary key alanlarını bir satır oluşturarak tutar. Eğer ilişkimiz 2 yönlü olacaksa pasif tarafı isteğimiz gibi belirleyip mappedBy özelliğini ekleriz. İlişkinin sahibi olan tarafa JoinTable notasyonunu ekleyerek oluşacak ara tablonun özelliklerini belirtebiliriz.

```java
@Entity
@Table(name = "M_T_M_STUDENT")
public class Student {

@ManyToMany
    @JoinTable(
            name = "M_T_M_STUDENT_LESSON",
            joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "LESSON_ID", referencedColumnName = "LESSON_ID")
    )
private List<Lesson> lessonList;
}

@Entity
@Table(name = "M_T_M_LESSON")
public class Lesson {

@ManyToMany(mappedBy = "lessonList")
private List<Student> studentList;
}
```

### JPA Criteria API

Entity sınıfları için sorgular oluşturmaya yarayan API’dir. JPQL’ e alternatiftir. En büyük avantajı sorgularda bir hata varsa derleme zamanında fark edilebilir olmasıdır.

Örnek bir criteria query:

```java
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
Root<Student> from = criteriaQuery.from(Student.class);

CriteriaQuery<Object> select = criteriaQuery.select(from);
TypedQuery<Object> typedQuery = entityManager.createQuery(select);
List<Object> resultList = typedQuery.getResultList();
```

Öncelikle criteriaBuilder nesnesi oluşturulur. Daha sonra builder kullanılarak bir criteiaQuery nesnesi oluşturulur. criteriaQuery.select ile sorgulanacak entity sınıfı belirtilir. TypedQuery sorguların dönüş tipini belirtilir.


### JPA Notasyonları

#### Sınıflar için geliştirilmiş notasyonlar

* Embeddable: Embeddable olarak işaretlenmiş olan Entity sınıfını başka bir Entity sınıfına gömebiliriz.

```java
@Embeddable 
public class PhoneNumber {
        private String areaCode;
        private String localNumber;
        ...
}
```

* Entity: Bir sınıfı entity sınıfı olarak işaretlemede kullanılır. Bu sayede veri tabanında ki bir tablo ile entity sınıfı eşleştirilecektir.

```java
@Entity
public class PhoneNumber{
    private String areaCode;
    private String localNumber;
    ...
}
```

* EntityListeners: Bu notasyon sayesinde veriler ile kalıcılık işlemi yapılması sırasında belirtilen fonksiyonlar çalıştırılabilir.

```java
@Entity
@EntityListeners({PrePersist.class})
class PhoneNumber {
    private String areaCode;
    private String localNumber;
    ...
}

class PrePersist {

    @PrePersist
    public void prePersist() {
        System.out.println("Pre Persist");
    }
}
```

* ExcludeDefaultListeners: Eğer tüm kalıcılık işlemlerinde sınıflara varsayılan bir dinleyici atamışsak ve bazı sınıfları varsayılan dinleyicilerden arındırmak istersek bu notasyonu kullanılırız. Bu işlem bu sınıftan kalıtım alan sınıflara da etki eder.

```java
@Entity
@ExcludeDefaultListeners
class PhoneNumber {
    private String areaCode;
    private String localNumber;
    ...
}
```

* ExcludeSuperclassListeners: Eğer bir sınıf için varsayılan bir dinleyici atanmışsa ve bu sınıftan kalıtım alınmışsa, dinleyici kalıtım alan sınıf içinde çalışmaktadır. Biz dinleyicimiz sadece üst sınıf için çalışsın istiyorsak kalıtım alan sınıfı bu notasyon ile işaretleriz.
```java
@Entity
@ExcludeSuperclassListeners
class Person extends PhoneNumber{
    
}
```

* IdClass: Composite primary key oluşturmak için kullanılır. Primary key alanlarının alınacağı sınfı belirtir.

```java
@IdClass(PersonPK.class)
@Entity
class Person {

}

class PersonPK {
    private int personNo;
    private int age;
    ...
}
```

Cacheable: Entity sınıfı için cache mekanizması aktif ya da pasif yapılır.

```java
@Entity
@Cacheable(false)
class PhoneNumber {
    private String areaCode;
    private String localNumber;
    ...
}
```

#### Fieldlar için geliştirilmiş notasyonlar

* Basic: Bir alanın null olup olamayacağı ya da fetch typeını belirtmemizi sağlar.

```java
@Basic(fetch=LAZY)
 private String localNumber;
```
* Embedded: Embeddable olarak işaretlenmiş olan sınıfı, asıl sınıfa gömmek için kullanılır. 

```java
@Embedded
public PhoneNumber phoneNumber;
```

* ElementCollection: Eklenecek olan embeddable sınıf ya da wrapper sınıfın(String, Integer vs.) liste olarak tanımlanması gerekiyorsa kullanılır.

```java
@ElementCollection
private Set<PhoneNumber> phoneNumbers = new HashSet();
```
* Id: Primary key alanını belirtmek için kullanılır.

```java
@Id
public Integer id;
```

* EmbeddedId: Embeddable olarak işaretmiş olan sınıfın alanlarından composite primary key belirtmek için kullanılır.

```java
 @EmbeddedId
private PhonePK phonePK;
```

* Version: Bir entitynin veri tabanına yeni kaydedilmesiyle 1 değerini alır ve her transaction da bir arttırılarak entity için versiyon numarası tutulur.

```java
@Version
private Integer versionNumber;
 ```

* Transient: Veri tabanında kalıcı olmasını istemediğimiz alanları bu notasyonla işaretleriz.

```java
@Transient
private String currentUser;
 ```

* Enumerated: Enum verinin nasıl saklanacağına karar verilir. EnumType.ORDINAL için sayısal değer EnumType.String için string değeri veritabanında kalıcı hale getirilir.

```java
@Enumerated(EnumType.ORDINAL)
private Day day1;

@Enumerated(EnumType.STRING)
private Day day2;
 ```

* Temporal: Date veya Calendar sınıf tipleri için sadece tarih, sadece zaman veya her ikisinde kalıcı hale getirileceği belirtilir.

```java
@Temporal(TemporalType.DATE)//Date only
private Date endDate;
```

#### İlişkiler için geliştirilmiş notasyonlar

* OneToOne: Birebir ilişkiler kurmak için kullanılır. Eğer ilişkide her iki taraf içinde bu ilişki tanımlanacaksa, ilişkide baskın tarafı ve pasif tarafı belirlememiz gerekir ve pasif tarafı mappedBy olarak belirtmemiz gerekir. Eğer ilişkiyi sadece tek taraflı belirtiyorsak buna gerek yok.

```java
@Entity
class Person {

    @OneToOne
    @JoinColumn
    private PersonInfo personInfo;
}

@Entity
class PersonInfo {

    @OneToOne(mappedBy="personInfo")
    private Person person;
}
```

* OneToMany ve ManyToOne: Bire çok ilişki kurmak için kullanılır. Eğer ilişkimizi iki yönlü tanımlıyorsak karşı taraf ManyToOne ilişkisinde olmalıdır. OneToMany taraf her zaman pasif taraftır ve mappedBy kullanılmalıdır. İlişkinin sahibi olan tarafada JoinColumn notasyonu eklenmelidir.

```java
@Entity
class Person {

    @OneToMany(mappedBy="person")
    private List<PhoneNumber> phoneNumberList;
}

@Entity
class PhoneNumber{

    @ManyToOne
    @JoinColumn
    private Person person;
}
```

* ManyToMany: N-N ilişkiler için kullanılır. Eğer ilişki çift yönlüyse pasif taraf seçilip mappedBy tanımlaması yapması gerekir. Diğer tarafa JoinColumn eklenir.

```java
@Entity
class Person {

    @ManyToMany
    @JoinColumn
    private List<Project> projectList;
}

@Entity
class Project {

    @ManyToMany(mappedBy="projectList")
    private List<Person> personList;
}
```

* OrderBy: Tanımlı liste veri tabanından çekildiğinde sıralama ölçütünü belirtir.

```java
@ManyToMany
@OrderBy("lastname ASC")
private List<Student> studentList;
```
#### Değer üretme için geliştirilmiş notasyonlar

* GeneratedValue: Primary key alanları için değer üretme stratejilerini belirler.

```java
@Id
@GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
public Integer id;
```

* SequenceGenerator: GeneratedValue notasyonunda değerlerin üretileceği sequence i belirtir. SequenceGenerator ile bir sequence oluşturulur ve GeneratedValue ile kullanılır.

```java
@Id
@SequenceGenerator(name="CUST_SEQ", allocationSize=1)
@GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
public Integer id;
```

* TableGenerator: Primary key değerlerinin bir tablo tarafından üretilmesini sağlanır.
```java
@TableGenerator(
            name="customGenerator",
            table="SEQ_TABLE",
            pkColumnName="ID",
            valueColumnName="VALUE",
            pkColumnValue="SEQ_ID",
            allocationSize=1)
@Id
@GeneratedValue(strategy=TABLE, generator="customGenerator")
int id;
```

#### Callback metotları için geliştirilmiş notasyonlar

* PrePersist: Yeni bir entity nesnesi kaydedilmeden önce çalışır.
* PostPersist: Yeni entity objesi veri tabanına kayıt edildikten sonra çalışır.
* PostLoad: Entity nesnleri veri tabanından alındığı zaman çalışır.
* PreUpdate: Var olan entity nesnesi veri tabanında  güncellenmeden önce çalışır.
* PostUpdate: Var olan entity nesnesi veri tabanında güncellendikten sonra çalışır.
* PreRemove: Var olan entity nesnesi veritabanından silinmeden önce çalışır.
* PostRemove: Var olan entity nesnesi veri tabanında silindikten sonra çalışır.

#### JPQL Sorguları içn geliştirilmiş notastonlar

* NamedQueries: NamedQuery için bir çerceve oluşturur. 

```java
@Entity
@NamedQueries({
        @NamedQuery(...),
        @NamedQuery(...)
})
class Project {

    @ManyToMany(mappedBy="projectList")
    private List<Person> personList;
}
```

* NamedQuery: JPQL Sorguları yazmamıza imkan tanıyan notasyon.

```java
@NamedQuery(name="findAllPersons", query="SELECT p FROM Person p")
```

* QueryHint: İlgili sorguya, timeout süresi, cache, maksimum satır sayısı gibi değeri atamamıza yarayan notasyon.

#### JAVA EE için geliştirilmiş notasyonlar

* PersistenceContext: EntityManager nesnesi için container oluşturarak yönetimini sağlar, Dependency Injection ile EntityManager nesnesinin yüklenmesini sağlar.

```java
@PersistenceContext 
EntityManager em;
```

* PersistenceContexts: Birden çok PersistenceContext tanımlaması için çerçeve oluşturur.

```java
@PersistenceContexts({
        @PersistenceContext(unitName="foo", name = "fooENC"),
        @PersistenceContext(unitName="bar", name = "barENC") }
)
public class EntityManagerProviderBean {
```

* PersistenceProperty: PersistenceContext nesnesi için çeşitli özellikler tanımlamaya imkan tanır.
* PersistenceUnit: EntityManagerFactory nesnesi için container oluştururarak nesnenin yönetimi ve yüklenmesinden sorumludur.

```java
@PersistenceUnit(unitName="defaultPersistenceUnit") 
private EntityManagerFactory emf;
```

#### Mapping işlemleri için geliştirilmiş notasyonlar

* AssociationOverride: Başka entity sınıfları ile ilişkisi(N-N, N-1 vs.) olan entity sınıfını ilişkileri override ederek kalıtım almak için kullanılır.

```java
@MappedSuperclass
public class Employee {
        ...
    @ManyToOne
    protected Address address;
        ...
}

@Entity
@AssociationOverride(name="address",
        joinColumns=@JoinColumn(name="ADDR_ID"))
// address field mapping overridden to ADDR_ID foreign key
public class PartTimeEmployee extends Employee {
        ...
}
```

* AttributeOverride: Kalıtım alınan sınıfın herhangi bir alanını override etmek için kullanılır.

* Column: Entity alanının veri tabanında karşılığı olan columna ait bilgiler girmemizi sağlar.

* JoinColumn: JoinColumn aralarında ilişki olan entity sınıfları için kullanılır. İlişkinin sahibini belirtir ve o ilişkiye ait spesifik özellikler tanımlamamızı sağlar.

* JoinTable: İlişki sonucu oluşan ara tabloya ait özellikleri tanımlayabileceğimiz notasyon.

* Lob: Büyük verilerin veri tabanında kalıcı olmasını sağlamak için kullanılır. Örneğin resmin byte halini veri tabanında tutmak için.

* Table: Entity sınıfının veri tabanında karşılık geldiği tabloya ait özelliklerin tanımlanabildiği notasyon.

