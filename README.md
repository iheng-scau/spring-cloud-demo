# spring-cloud-ext-project

#### Overview 
This project is aimed at extending origin feign client, adding some custom features into ordinary feign client provided
in spring cloud. Facilitating the spring boot auto-config and spring aop features, this library will make the usage of 
feign client more convenient.

#### Note
This library is still under developed and debugged, the features provided may functionally work but no guarantee is given
whether there is any fetal error occurred. This library is remained to be beta version util it is ready to be released as 
a formal version(e.g. version 1.0).

#### Dependency
The latest version should be `0.0.1`.
```xml
<dependency>
    <goupId>org.excode</goupId>
    <artifactId>spring-cloud-starter-ext</artifactId>
    <version>0.0.1</version>
</dependency>
```

#### Features
* Custom `ErrorDecoder` which enhances the default `ErrorDecoder` provided by spring cloud.
> This ErrorDecoder has the ability to decode two types of error response body.
* Custom `RetryPolicy`

#### Bugs Report
This bugs found will be listed in the table below.

|timestamp|bug desc|repaired version|note|
|:--------:|--------|:------:|-------|
|-|-|-|-|

#### Acknowledgement
This codes in this project is not origin from the project owner since some of them are referred from other 
project which may not be listed here yet. 
