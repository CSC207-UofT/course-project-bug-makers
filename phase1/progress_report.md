# Progress Report

![](progress_report.assets/p1demo.jpeg)

![](progress_report.assets/myCourseAppIntrop1.png)

## Goals for `Phase1`
- [x] TODO

## Table of Contents
1. [Successful Designs](#Successful-Designs) 
2. [Open Questions](#Open-Questions)
3. [Next Steps](#Phase-1-Progress-and-Next-Steps)


## Successful Designs
TODO:

## Use of GitHub Features
- Multiple issues were opened and resolved by pull requests to meet our `Phase1` goals, for example:
  - We established an auto-workflow, using `fix` statement to link the PR to the issue 
    - [`Issue #49`](https://github.com/CSC207-UofT/course-project-bug-makers/issues/49):
      - Conflicts arose within our dependencies under JDK16 environment, so we decided to downgrade it to JDK1.8
      - JDK successfully downgraded by merged pull requests: [`PR #50`](https://github.com/CSC207-UofT/course-project-bug-makers/pull/50), [`PR #51`](https://github.com/CSC207-UofT/course-project-bug-makers/pull/51)
  - We also labeled issues to keep our workspace organized.
    - [`Issue #45`](https://github.com/CSC207-UofT/course-project-bug-makers/issues/45) is labelled as enhancement
      - `userService` needs to support `reviewService`, so we decide to enhance their skeleton.
      - `userService` successfully supports operations on `review` entity groups with integrated database by merged pull requests: [`PR #47`](https://github.com/CSC207-UofT/course-project-bug-makers/pull/47)
- Over 90% of merged Pull Requests were reviewed and approved by other team members. The PR brings significant improvements in our team communication regarding:
  - New functionalities, and
  - Fixed bugs or typos, and
  - Codebase refactors.
- We used GitHub Actions, complimented with auto-testing Workflow, to keep our codebase robust and reduce potential runtime errors.

##Testing
- We achieved FULL test coverage for the testable Service Controllers. **73%** of the methods in our system were covered by our test cases.
- We introduce randomness in the code-test system, to ensure test case comprehensiveness.
  - We make `userRegister()` testable by generating random Usernames.
- In the most challenging database test, for the data access objects, we developed a full set of approaches to test the codes without interfering the normal functionality of our database.
  - In the `createNewCourse()`, we introduced randomness to avoid duplication in the database.
  - In the `UserServiceControllerTest`, after each test case, we empty the Lists to prevent subsequent changes in the database.
- Difficulties while testing:
  - Some operations are irreversible
  - Some private fields and methods are not reachable.

## Open Questions
- TODO:


## Phase 1 Progress and Next Steps
- We have `k` lines implemented/optimized in our `Phase 1` project in total.
- [Kuan Pang]
- [Michael Fang]
- [Li Quan Soh]
- [Kevin Cheng]
- [Jiaming Weng]
- [Sherry You]


