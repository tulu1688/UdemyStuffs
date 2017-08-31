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
> public class User{
>   @Id
>   private Long userId; // Basic value type  
>   private String firstName; // Basic value type  
>   private String lastName; // Basic value type  
>   private List<String> aliases; // Collection value type  
>   private Address address; // Composite type  
> }
>
> public class Address{
>   private String addressLine1;
>   private String addressLine2;
>   private String city;
>   private String state;
>   private String zipCode;
>   private String addressType;
> }

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
> @ElementCollection  
> @CollectionTable(name="BANK_CONTACT",  joinColumns=@JoinColumn(name="BANK_ID"))  
> @MapKeyColumn(name="POSITION_TYPE")  
> @Column(name="NAME")  
> private Map<`String`, `String`> contacts = new HashMap<`String`, `String`>();  
