<p align="center">
     This project is a multi-service system centered on Ahrefs Keyword Suggestions. It features a REST API, XSD and RELAX NG validation, a SOAP service that runs XPath searches over generated XML and an XML-RPC service for looking up weather reports. 
</p>

## 📌 About The Project

Built as an interoperability demo to promote loose coupling, this system uses Ahrefs Keyword Suggestions as a shared data model across REST, SOAP, and XML-RPC components. 

The REST layer exposes a JWT-protected CRUD API with access and refresh tokens for controlled access, and before any record is persisted its XML is validated against both XSD and RELAX NG schemas. The SOAP service provides a search operation over an XML snapshot of the data. Rather than querying the database directly, it evaluates an XPath expression against the XML to select only the elements that meet the chosen criteria. A separate XML-RPC service returns current temperatures from the official [DHMZ feed](<https://vrijeme.hr/hrvatska_n.xml>).

Client written in Blazor ties everything together by calling all the services to demonstrate their functionalities. 

## 🛠 Built With

### Languages & Frameworks
![C#](https://img.shields.io/badge/c%23-%23239120.svg?style=for-the-badge&logo=csharp&logoColor=white)
![ASP.NET](https://img.shields.io/badge/ASP.NET-512BD4?style=for-the-badge&logo=dotnet&logoColor=white)
![Blazor](https://img.shields.io/badge/blazor-%235C2D91.svg?style=for-the-badge&logo=blazor&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)

### **Libraries & Tools**
[![XSD](https://img.shields.io/badge/XSD-34D399?style=for-the-badge)](https://www.w3.org/TR/xmlschema11-1/)
[![RELAX NG](https://img.shields.io/badge/RELAX%20NG-A855F7?style=for-the-badge)](https://relaxng.org/)
[![SOAP](https://img.shields.io/badge/SOAP-0A66C2?style=for-the-badge)](https://www.w3.org/TR/soap12/)
[![XPath](https://img.shields.io/badge/XPath-F59E0B?style=for-the-badge)](https://www.w3.org/TR/xpath-31/)
[![JAXB](https://img.shields.io/badge/JAXB-0F766E?style=for-the-badge)](https://jakarta.ee/specifications/xml-binding/)
[![XML-RPC](https://img.shields.io/badge/XML%E2%80%91RPC-EF4444?style=for-the-badge)](https://www.xmlrpc.com/spec)
![Spring Security](https://img.shields.io/badge/Spring%20Security-1D4ED8?style=for-the-badge&logo=springsecurity&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)

### Storage
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)


