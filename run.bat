:start 
javac -cp .;json-simple-1.1.1.jar Jsonview.java
jar -cvfm Jsonviewer.jar manifest.mf -C ./ .
rem cmd/k
