# huula coding challenge!


## Prerequisites

- Java 8 (Jdk 1.8) 
- Maven

## clone to local

    git clone https://github.com/nicelord/huula-coding-challenge.git

## run the tests

    mvn test

example output :

    D:\projects\IdeaProjects\hoolah-coding-challenge>mvn test  
    [INFO] Scanning for projects...  
    [INFO]  
    [INFO] ------------------< dev.riza:huula-coding-challenge >-------------------[INFO] Building huula-coding-challenge 1.0-SNAPSHOT  
    [INFO] --------------------------------[ jar ]---------------------------------  
    [INFO]  
    [INFO] --- maven-resources-plugin:2.6:resources  (default-resources) @ huula-coding-challenge ---[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources, i.e. build is platform dependent!  
    [INFO] Copying 0 resource  
    [INFO]  
    [INFO] --- maven-compiler-plugin:3.8.1:compile  (default-compile) @ huula-coding-challenge ---[INFO] Changes detected - recompiling the module!  
    [WARNING] File encoding has not been set, using platform encoding Cp1252, i.e. build is platform dependent!  
    [INFO] Compiling 13 source files to D:\projects\IdeaProjects\hoolah-coding-challenge\target\classes  
    [INFO]  
    [INFO] --- maven-resources-plugin:2.6:testResources  (default-testResources) @ huula-coding-challenge ---[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources, i.e. build is platform dependent!  
    [INFO] Copying 5 resources  
    [INFO]  
    [INFO] --- maven-compiler-plugin:3.8.1:testCompile  (default-testCompile) @ huula-coding-challenge ---[INFO] Changes detected - recompiling the module!  
    [WARNING] File encoding has not been set, using platform encoding Cp1252, i.e. build is platform dependent!  
    [INFO] Compiling 5 source files to D:\projects\IdeaProjects\hoolah-coding-challenge\target\test-classes  
    [INFO]  
    [INFO] --- maven-surefire-plugin:3.0.0-M3:test  (default-test) @ huula-coding-challenge ---[INFO]  
    [INFO] -------------------------------------------------------  
    [INFO] T E S T S  
    [INFO] -------------------------------------------------------  
    [INFO] Running AmountTest  
    [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.02 s - in AmountTest  
    [INFO] Running CSVScanServiceTest  
    [INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.009 s - in CSVScanServiceTest  
    [INFO] Running MerchantTest  
    [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in MerchantTest  
    [INFO] Running TransactionAnalyzerServiceTest  
    [INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.123 s - in TransactionAnalyzerServiceTest  
    [INFO] Running TransactionTest  
    [INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in TransactionTest  
    [INFO]  
    [INFO] Results:  
    [INFO]  
    [INFO] Tests run: 23, Failures: 0, Errors: 0, Skipped: 0  
    [INFO]  
    [INFO] ------------------------------------------------------------------------  
    [INFO] BUILD SUCCESS  
    [INFO] ------------------------------------------------------------------------  
    [INFO] Total time: 3.094 s  
    [INFO] Finished at: 2019-12-04T01:52:18+07:00  
    [INFO] ------------------------------------------------------------------------ 

## Build

    mvn clean package
example output : 

    D:\projects\IdeaProjects\hoolah-coding-challenge>mvn clean package  
    [INFO] Scanning for projects...  
    [INFO]  
    [INFO] ------------------< dev.riza:huula-coding-challenge >-------------------[INFO] Building huula-coding-challenge 1.0-SNAPSHOT  
    [INFO] --------------------------------[ jar ]---------------------------------  
    [INFO]  
    [INFO] --- maven-clean-plugin:2.5:clean  (default-clean) @ huula-coding-challenge ---[INFO] Deleting D:\projects\IdeaProjects\hoolah-coding-challenge\target  
    [INFO]  
    [INFO] --- maven-resources-plugin:2.6:resources  (default-resources) @ huula-coding-challenge ---[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources, i.e. build is platform dependent!  
    [INFO] Copying 0 resource  
    [INFO]  
    [INFO] --- maven-compiler-plugin:3.8.1:compile  (default-compile) @ huula-coding-challenge ---[INFO] Changes detected - recompiling the module!  
    [WARNING] File encoding has not been set, using platform encoding Cp1252, i.e. build is platform dependent!  
    [INFO] Compiling 13 source files to D:\projects\IdeaProjects\hoolah-coding-challenge\target\classes  
    [INFO]  
    [INFO] --- maven-resources-plugin:2.6:testResources  (default-testResources) @ huula-coding-challenge ---[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources, i.e. build is platform dependent!  
    [INFO] Copying 5 resources  
    [INFO]  
    [INFO] --- maven-compiler-plugin:3.8.1:testCompile  (default-testCompile) @ huula-coding-challenge ---[INFO] Changes detected - recompiling the module!  
    [WARNING] File encoding has not been set, using platform encoding Cp1252, i.e. build is platform dependent!  
    [INFO] Compiling 5 source files to D:\projects\IdeaProjects\hoolah-coding-challenge\target\test-classes  
    [INFO]  
    [INFO] --- maven-surefire-plugin:3.0.0-M3:test  (default-test) @ huula-coding-challenge ---[INFO]  
    [INFO] -------------------------------------------------------  
    [INFO] T E S T S  
    [INFO] -------------------------------------------------------  
    [INFO] Running AmountTest  
    [INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.022 s - in AmountTest  
    [INFO] Running CSVScanServiceTest  
    [INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.008 s - in CSVScanServiceTest  
    [INFO] Running MerchantTest  
    [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in MerchantTest  
    [INFO] Running TransactionAnalyzerServiceTest  
    [INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.127 s - in TransactionAnalyzerServiceTest  
    [INFO] Running TransactionTest  
    [INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in TransactionTest  
    [INFO]  
    [INFO] Results:  
    [INFO]  
    [INFO] Tests run: 23, Failures: 0, Errors: 0, Skipped: 0  
    [INFO]  
    [INFO]  
    [INFO] --- maven-jar-plugin:3.1.2:jar  (default-jar) @ huula-coding-challenge ---[INFO] Building jar: D:\projects\IdeaProjects\hoolah-coding-challenge\target\huula-coding-challenge-1.0-SNAPSHOT.jar  
    [INFO] ------------------------------------------------------------------------  
    [INFO] BUILD SUCCESS  
    [INFO] ------------------------------------------------------------------------  
    [INFO] Total time: 3.516 s  
    [INFO] Finished at: 2019-12-04T01:55:14+07:00  
    [INFO] ------------------------------------------------------------------------



## run the program

    java -jar target\huula-coding-challenge-1.0-SNAPSHOT.jar "input.csv" "Kwik-E-Mart" "20/08/2018 12:00:00" "20/08/2018 13:00:00"

example output :

    D:\projects\IdeaProjects\hoolah-coding-challenge>java -jar target\huula-coding-challenge-1.0-SNAPSHOT.jar "input.csv" "Kwik-E-Mart" "20/08/2018 12:00:00" "20/08/2018 13:00:00"  
    Number of transactions : 1  
    Average transaction value : 59.99
