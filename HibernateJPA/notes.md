# My tips
- The best way to map a projection query to a DTO (Data Transfer Object) with JPA and Hibernate. [link](https://vladmihalcea.com/2017/08/29/the-best-way-to-map-a-projection-query-to-a-dto-with-jpa-and-hibernate/)
- High performance JDBC and Hibernate [link](https://vladmihalcea.com/books/high-performance-java-persistence/)

# Hibernate technical overview
Hibernate = Hibernate configuration + Annotation (mapping metadata) + Session (create session, invoke persistence methods)  
## Cross cutting concern in Application
* Security
* Validation
* Error Handling
* Logging
* Data persistence
> Persistence refer to the characteristic of state that outlives the process that created it. Without this capability, state would only exist in RAM, and would be lost when this RAM loses power, such as a computer shutdown.

## Maven dependancies
1. hibernate-core
2. hibernate-annotations

# Hibernate basic
## Simple entity mapping
1. Annotation `entity` for POJO class
2. Annotation `table` for table name in database
3. Annotation `Column` for column name of field in database
4. Annotation `Id` for the id field in database
5. Annotation `GeneratedValue` for fields that their value will be automatically generated  

## Building a session factory
- Using singleton
- Session.refresh(entity) -> re select entity value

# Basic Mapping Annotation
## JPA Annotations
- Defined by the JPA 2.0 Specification (JSR 317)
- Provides the standard API on which the ORM vendors should base their implementation
- Packaged in `javax.persistence`

## Hibernate Annotations
- Provided features beyond those specified in JPA
- Couples the application to Hibernate
- Packaged in `org.hibernate.annotations`
- Hibernate inherits JPA

## Annotations
- Access: type FIELD_ACCESS, PROPERTIES_ACCESS
- Column: updateable: fale/true; nullable: false/true
- Id: Surrogate key and Natural key
> GeneratedValue: strategy: SEQUENCE, IDENTITY, TABLE, `AUTO -> default`; generator: `gen_name`  
> SequenceGenerator: name: `seq_name`, sequenceName: `seq_name_indb`  
> TableGenerato: name: `table_name`, table, pkColumnName, valueColumnName  

- Transient: when we don't want include one field to hibernate mapping. Hibernate will not treat this field as a column. This field will not be insert or update to real db.
- Temporal: TIMESTAMP, DATE, TIME. We use to avoid casting problem between field types.
- Formula: calculate in time when execute query
> @Formula("lower(datediff(curdate(), birth_date))/365")  
> private int age;  

## Hibernate types
- Entity Types: corresponds with a database row, have a database entity
- Value Types: no database identity. Basic, Composite Types, Collections

```
public class User{
  @Id
  private Long userId; // Basic value type  
  private String firstName; // Basic value type  
  private String lastName; // Basic value type  
  private List<String> aliases; // Collection value type  
  private Address address; // Composite type  
}

public class Address{
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String zipCode;
  private String addressType;
}
```

- Some Basic Hibernate types: integer, long, short, float, double, character, byte, boolean, yes_no, true_false, string, date, time, timestamp, calendar, calendar_date, big_decimal, big_integer, locale, timezone, currency, class, binary, text, image, clob, blob

# Mapping composite and collection value type
## Composite value type
- Represent a group of values in a single Java type
- Embedded (JPA)/ Composite Type (Hibernate)
- Does not have an Id or table
- use `Embedded` with `AttributeOverrides`

## Collection value type
- Can define a collection of basic or composite value types
- Composites/basics are stored in a separate collection table
- Do not have a unique identity
- Annotations: `ElementCollection`, `CollectionTable`, `JoinColumn`, `Column`

## Mapping a map of basic values
Example  
```
@ElementCollection  
@CollectionTable(name="BANK_CONTACT",  joinColumns=@JoinColumn(name="BANK_ID"))  
@MapKeyColumn(name="POSITION_TYPE")  
@Column(name="NAME")  
private Map<`String`, `String`> contacts = new HashMap<`String`, `String`>();  
```

# Entity Associations
- Provide mapping metadata for various associations
- Map collections of entity types
- Type of Entity Associations: `One to One`, `One to Many`, `Many to One`, `Many to Many`

## One to one associations
- Annotations: `OneToOne` and specify the __cascadeType__ for `unidirectional association`, __mappedBy__ for `bidirectional association`, `JoinColumn`
- Two mode of One-To-One association: unidirectional, bidirectional
- Using `bidirectional association` in non owning Entity, `unidirectional association` in owning Entity

## Unidirectional OneToMany associations
- Annotations: `OneToMany` and specify the __cascadeType__, `JoinColumn` set __nullable = false__ to guide Hibernate update the `Id` of non-owning table to owning table

## Bidirectional OneToMany associations
- Annotations: set `ManyToOne` in owning Entity, `JoinColumn` and specify the __name__ of mapped column; set `OneToMany` in non-owning Entity and specify the __mappedBy__ to `field_name` of non-owning Entity in owning Entity.

## @JoinTable Annotation
- When we want to link two entity. But in non-owning entity there are at least 2 foreign keys, we should use the `@JoinTable` annotation with __name__ is the non-owning table name, __joinColumns__ is the name of foreign keys, __inverseJoinColumns__ is the name of the key on owning table

## Unidirectional ManyToMany associations
- First select one Entity to be the owning Entity, use `JoinTable` annotation because we have at least 2 foreign keys for both 2 entity in Mutual table, set the __joinColumns__ and __inverseJoinColumns__ as well.

## Bidirectional ManyToMany associations
- In non-owning Entity use `ManyToMany` annotation and select __mappedBy__ is the `field_name` of non-owning Entity in owning Entity.

# Hibernate API
## Persistence lifecycle
Transient --> Persistence Context (Persistent & Remove) --> Detached  
__Transient state__ object constructed with the new operator that is not associated with any database row  
__Persistent state__ an entity instance associated with a database row and contained within a persistence context  
__Removed state__ an object scheduled for deletion  
__Detached state__ object references to an entity once associated with a closed persistence context  

__Persistence context__ corresponds with a session, cache of all persistent entity instances  

Example:  
```
Bank bank = new Bank(); // Transient  
SessionFactory factory = HibernateUtil.getSessionFactory();  
Session session = factory.openSession(); // Persistence Context 1  
Transaction transaction = session.beginTransaction();
session.save(bank); // Persistent  
transaction.commit();  
session.close(); // Close Persistence Context 1  

// bank is now detached  
Session session2 = factory.openSession(); // Persistence Context 2  
Transaction transaction2 = session2.beginTransaction();  
session2.delete(bank); // removed (Hibernate only)  
transaction2.commit();  
session2.close(); // Close Persistence Context 2
```

## Saving Entities
session.save(entity);

## Retrieving Entities
There are 2 methods to retrieve entities:
1. session.get(className, id); // when not found id in db -> entity is NULL
2. session.load(className, id); // lazy load. When not found id in db -> throw exception. When object is really used, session will retrieve object from database  

## Modifying Entities
N/A

## Removing Entities
session.delete(entity);

## Reattaching detached entities
Entity entity = session1.get(ClassName, id);
session1.close();  
session2.update(entity); // reattach  

## Save or Update
`session.saveOrUpdate(entity);` // this functions work with both transient entity and detached entity  

## Flushing the persistence context
`session.flush(entity);` // Synchronize persistence context and database without commit the session  

# Java Persistence API (JPA)
| __Entity Manager__ | __Session__ |
|---|---|
| persist() | save() |
| merge() | merge() |
| remove() | delete() |
| detached(), close(), clear() | evict(), close(), clear() |
| find(), getReference() | get(), load() |
| flush() | flush() |

Hibernate is JPA implementation. JPA is a standard that Java community has developed.

## JPA configuration
- Add hibernate-entitymanager to pom file

Example
```
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
 http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd" version="2.1">

 <persistence-unit name="infinite-finances">
   <provider>org.hibernate.ejb.HibernatePersistence</provider>
   <properties>
     <property name="javax.persistence.jdbc.user" value="infinite"/>
     <property name="javax.persistence.jdbc.password" value="skills"/>
     <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/ifinances"/>
     <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>

     <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLInnoDBDialect"/>
     <property name="hibernate.show_sql" value="true"/>
   </properties>
 </persistence-unit>
</persistence>
```

## Saving Entities
`entityManager.persist(entity);`

## Retrieving Entities
`entityManager.find(ClassName, id);` // when not found id in db the entity is Null
`entityManager.getReference(ClassName, id);` // lazy load version. When not found id in db throw exception  

## Modifying Entities
N/A

## Removing Entities
`entityManager.remove(entity);`

## Reattaching the detached entities
`entityManager.clear();` // detached all entities from persistence context  
`entityManager.detach(entity);` // detach single entity from persistence context  
`ontherEntity = entityManager.merge(detachedEntity);` // reattach detached entity to a persistence context  

## Session and EntityManager similarities

# Advanced Mappings and configuration
## Compound Primary keys
Using `IdClass` annotation when work with compound primary keys  

## Compound Join Column
Using `ManyToOne` annotation, `cascade` annotation, `JoinColumns` show all foreign keys in array  
```
@ManyToOne(cascade=CascadeType.ALL)
@JoinColumns({
  @JoinColumn(name="CURRENCY_NAME", referencedColumnName="NAME"),
  @JoinColumn(name="COUNTRY_NAME", referencedColumnName="COUNTRY_NAME")
})
```

## Enumeration
Using `Enumerated` annotation and should use the `EnumType.String`  

## Mapped superclass inheritance
Use `MappedSuperclass` annotation for the parent class in a inheritance association  

## Table per class inheritance
- Dont really understand :(  

## Single table strategy
- Dont really understand :(  

## Building a persistence layer

## Schema generation
```
<property name="hbm2dll.auto">create/validate/update/create-drop</property>
```

# Hibernate query language and Java persistence query language

## Join in HQL, JPQL

## Lazy load
Change the `fetch` feature in `ManyToOne` or `OneToMany` to `LAZY` or `EAGER`  

# Criteria API
Criteria API allows create complex queries via Criteria (Hibernate), and CriteriaQuery (JPA)  
