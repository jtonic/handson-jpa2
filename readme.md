Issues, corner-cases and pitfalls discovered while working with JPA2 Hibernate issue. For such cases I tried to find solutions with the Hibernate Extensions or somehow modify the correct ORM relations to handle them.

1. The laziness of the @Lob @Basic(fetch = FetchType.LAZY) private Blob content; is not provided by JPA2 Hibernate implementation with Oracle implementation, because it is based on the laziness of the underlying driver Blob implementation, and in the case of Oracle driver the implementation is eager.

    Solutions:


2. A solution to pass from client an getting back an InputStream, and , behind the scene Hibernate/DB use Blob

    Solutions


How to run the examples:
1. Create the following os env variables:
    ORACLE_JDBC_DRIVER
    ORACLE_JDBC_URL
    MW_USERNAME
    MW_PASSWORD
2. Run the src/db/createschema.sql script
3. The example is using the JDBC driver which, unfortunately is not published in a public mvn repo. Consequently we have to use a local jar. downloaded from oracle sites. So, download it and modify the mvn profile "at_home" in pom.xml, as per below:
            <profile>

                <id>at_home</id>

                ...

                    <systemPath>${path_to_jdbc_driver}/ojdbc6.jar</systemPath>

4. Run the testng tests:
    ro.jtonic.handson.jpa2.BlobExampleTest.testSaveAndGetFileContent (blob example)
    ro.jtonic.handson.jpa2.ManyToOneRelationTest (ManyToOne example with different test cases but all have the same thing in common, there is no FK relation between the related tables).







