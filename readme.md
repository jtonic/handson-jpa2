Issues, corner-cases and pitfalls discovered while working with JPA2 Hibernate issue. For such cases I tried to find solutions with the Hibernate Extensions or somehow modify the correct ORM relations to handle them.

1. The laziness of the @Lob @Basic(fetch = FetchType.LAZY) private Blob content; is not provided by JPA2 Hibernate implementation with Oracle implementation, because it is based on the laziness of the underlying driver Blob implementation, and in the case of Oracle driver the implementation is eager.

    Solutions:


2. A solution to pass from client an InputStream and getting back an InputStream (sounds strange, I know) and Hibernate/DB use Blob.

    Solutions



