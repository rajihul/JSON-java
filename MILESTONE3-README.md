**Performance Implications of doing this inside the 
library vs. doing it in client code:**

Performing a transformation on all the keys of the resulting JSON object is
much more efficient when done inside the library for two reasons:
1. You can modify the key as you're reading the XML file and generating the 
JSON object versus generating the entire JSON Object first and then going back
to parse the entire structure again and updating the keys.

2.  You can provide your own custom transformations which can be
handled dynamically thanks to the functional interface built into 
Java 8.