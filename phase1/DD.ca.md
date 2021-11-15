

- Clean Architecture
  - Overall structure
    - Our app consists of 4 controllers, 11 use cases and 4 entity groups, where the overall structure can be demonstrated in the following architecture diagram.
      ![](designdocument.assets/p1ca.jpg)
    - Our architecture design strictly follows the clean architecture principle, where code dependencies are unidirectional, going from outer layer to the inner layer.
    - Our controller layer (or Interface Adaptor layer) are designed to be the only layer exposing to front-end, it interacts with our use case layer (or Application Business Layer) to execute user's request.
    - Our use case layer then interacts with our entity layer (or Enterprise Business Rules). As we have connection with database, there is one extra DAO layer (classified as Application Business Rule) to perform the data I/O requests.
  - Example: Review Service Branch, a layer segregation design
    - As shown in following graph, we demonstrate a clean architecture in our Review Service Branch. Begin with our user sending a review creation request to `Reveiw Service Controller`. Our `Review Service Controller` calls `Recommendation Request Request Processor` (use case) to generate a recommendation score of user review. The generation of machine learning recommendation score is running on our remote inference server, where the connection to inference server is established by `Inference DAO` (use case) without violating clean architecture principle.
    - Once obtained a response from inference server, our controller calls `Recommendation Request Processor` (use case), to config a review entity and then update to the database through `Review DAO`.
    - Therefore, our dependency is constructing from outer layer to inner layer without bypassing intermediates. Thus, strictly following clean architecture principle.
    - ![](designdocument.assets/reviewca.png)