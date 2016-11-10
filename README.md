# interfacing R with scala

I try to get scala to talk to R. The rscala package seems to be nice, however I am facing a strange problem:

```
git clone https://dahl-git.byu.edu/dahl/rscala.git
cd rscala
git checkout f561a84c985b1ee0ba115e397d69b996f484a817
sbt +publishM2
```
This builds the packages.
    
Then executing a sbt run in this project produces no longer an error. 

The latest version (SNAPSHOT) results in the following error

```
java.lang.NullPointerException
java.lang.NullPointerException
        at java.io.Reader.<init>(Reader.java:78)
        at java.io.InputStreamReader.<init>(InputStreamReader.java:129)
        at scala.io.BufferedSource.reader(BufferedSource.scala:24)
        at scala.io.BufferedSource.bufferedReader(BufferedSource.scala:25)
        at scala.io.BufferedSource.scala$io$BufferedSource$$charReader$lzycompute(BufferedSource.scala:35)
        at scala.io.BufferedSource.scala$io$BufferedSource$$charReader(BufferedSource.scala:33)
        at scala.io.BufferedSource.scala$io$BufferedSource$$decachedReader(BufferedSource.scala:62)
        at scala.io.BufferedSource$BufferedLineIterator.<init>(BufferedSource.scala:67)
        at scala.io.BufferedSource.getLines(BufferedSource.scala:86)
        at org.ddahl.rscala.RClient$$anonfun$7.apply(RClient.scala:693)
        at org.ddahl.rscala.RClient$$anonfun$7.apply(RClient.scala:692)
        at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:234)
        at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:234)
        at scala.collection.immutable.List.foreach(List.scala:381)
        at scala.collection.TraversableLike$class.map(TraversableLike.scala:234)
        at scala.collection.immutable.List.map(List.scala:285)
        at org.ddahl.rscala.RClient$.apply(RClient.scala:692)
        at org.ddahl.rscala.RClient$.apply(RClient.scala:668)
        at ScalaToRTest$.main(Testing.scala:17)
        at ScalaToRTest.main(Testing.scala)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
```
## calling a function
- see the sample code in the scala file
- works just fine

