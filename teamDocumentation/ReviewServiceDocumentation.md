# ReviewService Documentation

## Design Goal
- Provide review service for our users.
  - User can add our edit their review to a specific instructor under a course.
  - Instructor recommendation is provided to help the user make a better choice by machine learning approche.

## Overall Structure
- The overall structure can be divided into multiple parts, following interface-orientated programming
  - `ReviewServiceController`
  - `ReviewRequestProcessor`, `RecommendationRequestProcessor`
  - `ReviewServiceDAO`, `InferenceDAO`
- Our controller `ReviewServiceController` serves as an API exposed to our user, it presents the review summary to a specific course/instructor/user. It also helps to submit user review or edit user review.
- For `ReviewRequestProcessor` and `RecommendationRequestProcessor`, these two service use cases help to mediate the requests and generate recommendation scores to rank the instructor.
- For `ReviewServiceDAO` and `InferenceDAO`, these two `DAO` helps to query the information from database or query the inference result form our inference server. 

## ReviewServiceController
- General Responsibility:
    - Execute user's writing or reading request to the review system.  
- Check the Jdoc for detail.



    

