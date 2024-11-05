1. What are the differences between Stax, SAX, DOM? When each parser will be used?
Stax(Streaming API for XML) has a pull-parsing style, meaning that it does not load the entire xml file at once, but rather pulls events as needed. It is bidirectional, so Stax can move forwards and backwards in the xml file. It can read and write an xml file. SAX(Simple API for XML) is event-driven, meaning that it reads an XML file sequentially in a forward-only manner, and triggers events like startElement, endElement, etc. It is similar to Stax in that it also does not need to load the entire document into memory. However, SAX can only read xml, and not change or manipulate the data of an xml file. DOM(Document Object Model) is tree-based, which means that it parses the entire XML document at once, and creates an in-memory tree structure which represents the XML file. It can be fully navigated through and can be used to read and write to an XML file.

2. What is an XSD schema? How to validate XML using schema?
An XSD schema is a way to describe the structure and validate the contents of an XML file. It defines the elements, attributes, and data types that are allowed in an XML document to enforce consistency and data integrity. Using JAXB, you will first create a SchemaFactroy object that can then be used to load the XSD file to create a Schema object that the marshaling and unmarshalling can be set from. 

3. How to represent Array/Date/complex object in XML?
Arrays are represented typically with a parent element name (usually a name for the list) and then the child elements are typically the elements of the array. Dates are often represented as strings in a standard format, such as YYYY-MM-DD. Complex objects are usually represented by nesting XML elements. Each sub-element corresponds to a property of the object.

4. What is attribute? When will you use attribute and when tags?
Attributes are used to provide supplementary information about an element. They are placed within the opening tag for an element, and consist of a name-value pair. 

5. How to parse arrays/dates/complex objects using Jaxb?
Arrays can be parsed using Jaxb by directly annotating the array as a parent element. If the elements of the array are complex objects, then then the class for those objects must also be annotated. Dates can be parsed by creating an adapter class to convert the date to a string and using a SimpleDateFormat to parse using Jaxb. You can also do this directly in the original class as well without using an adapter class, you would just have to properly annotate and use the correct getters and setters for a specific jaxbDate field, and then use @XMLTransient to ignore the original date field.

6. What annotations do you know? Provide the definitions for them?
@XMLRootElement sets the name of the root tag and sets the root element to be parsed. @XMLElement sets the name for the child elements tag and sets the child element to be parsed. 
@XMLTransient is used to ignore certain fields of a class. 
@XMLAccessorType is used to set the access level for Jaxb parsing. 

7. How to prevent serialization/deserialization for properties?
@XMLTransient is the annotation that is used to tell the Jaxb parser to ignore certain fields for serialization and deserialization.

8. How to parse arrays/dates/complex objects using Jackson?
@JsonProperty sets the property element name and fields to be parsed.

9. What annotations do you know? Provide the definitions for them?
@JsonProperty which sets the property element name and field to be parsed.
@JsonIgnore is used to ignore certain fields to prevent serialization/deserialization.

10. How prevent serialization/desirialization for properties?
@JsonIgnore is used to ignore certain fields to prevent serialization/deserialization.
