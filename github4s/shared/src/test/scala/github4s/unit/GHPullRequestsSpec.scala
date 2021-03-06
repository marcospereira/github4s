/*
 * Copyright 2016-2019 47 Degrees, LLC. <http://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github4s.unit

import cats.free.Free
import github4s.GHPullRequests
import github4s.GithubResponses.{GHResponse, GHResult}
import github4s.app.GitHub4s
import github4s.free.domain._
import github4s.utils.BaseSpec

class GHPullRequestsSpec extends BaseSpec {

  "GHPullRequests.get" should "call to PullRequestOps with the right parameters" in {

    val response: Free[GitHub4s, GHResponse[PullRequest]] =
      Free.pure(Right(GHResult(pullRequest, okStatusCode, Map.empty)))

    val pullRequestOps = mock[PullRequestOpsTest]
    (pullRequestOps.getPullRequest _)
      .expects(validRepoOwner, validRepoName, validPullRequestNumber, sampleToken)
      .returns(response)

    val ghPullRequests = new GHPullRequests(sampleToken)(pullRequestOps)
    ghPullRequests.get(validRepoOwner, validRepoName, validPullRequestNumber)
  }

  "GHPullRequests.list" should "call to PullRequestOps with the right parameters" in {

    val response: Free[GitHub4s, GHResponse[List[PullRequest]]] =
      Free.pure(Right(GHResult(List(pullRequest), okStatusCode, Map.empty)))

    val pullRequestOps = mock[PullRequestOpsTest]
    (pullRequestOps.listPullRequests _)
      .expects(validRepoOwner, validRepoName, Nil, sampleToken, Some(Pagination(1, 10)))
      .returns(response)

    val ghPullRequests = new GHPullRequests(sampleToken)(pullRequestOps)
    ghPullRequests.list(validRepoOwner, validRepoName, pagination = Some(Pagination(1, 10)))
  }

  "GHPullRequests.listFiles" should "call to PullRequestOps with the right parameters" in {

    val response: Free[GitHub4s, GHResponse[List[PullRequestFile]]] =
      Free.pure(Right(GHResult(List(pullRequestFile), okStatusCode, Map.empty)))

    val pullRequestOps = mock[PullRequestOpsTest]
    (pullRequestOps.listPullRequestFiles _)
      .expects(validRepoOwner, validRepoName, validPullRequestNumber, sampleToken, None)
      .returns(response)

    val ghPullRequests = new GHPullRequests(sampleToken)(pullRequestOps)
    ghPullRequests.listFiles(validRepoOwner, validRepoName, validPullRequestNumber)
  }

  "GHPullRequests.createPullRequestData" should "call to PullRequestOps with the right parameters" in {

    val response: Free[GitHub4s, GHResponse[PullRequest]] =
      Free.pure(Right(GHResult(pullRequest, okStatusCode, Map.empty)))

    val pullRequestOps = mock[PullRequestOpsTest]
    (pullRequestOps.createPullRequest _)
      .expects(
        validRepoOwner,
        validRepoName,
        validNewPullRequestData,
        validHead,
        validBase,
        Some(true),
        sampleToken)
      .returns(response)

    val ghPullRequests = new GHPullRequests(sampleToken)(pullRequestOps)
    ghPullRequests.create(
      validRepoOwner,
      validRepoName,
      validNewPullRequestData,
      validHead,
      validBase,
      Some(true))
  }

  "GHPullRequests.createPullRequestIssue" should "call to PullRequestOps with the right parameters" in {

    val response: Free[GitHub4s, GHResponse[PullRequest]] =
      Free.pure(Right(GHResult(pullRequest, okStatusCode, Map.empty)))

    val pullRequestOps = mock[PullRequestOpsTest]
    (pullRequestOps.createPullRequest _)
      .expects(
        validRepoOwner,
        validRepoName,
        validNewPullRequestIssue,
        validHead,
        validBase,
        Some(true),
        sampleToken)
      .returns(response)

    val ghPullRequests = new GHPullRequests(sampleToken)(pullRequestOps)
    ghPullRequests.create(
      validRepoOwner,
      validRepoName,
      validNewPullRequestIssue,
      validHead,
      validBase,
      Some(true))
  }

  "GHPullRequests.listReviews" should "call to PullRequestOps with the right parameters" in {

    val response: Free[GitHub4s, GHResponse[List[PullRequestReview]]] =
      Free.pure(Right(GHResult(List(pullRequestReview), okStatusCode, Map.empty)))

    val pullRequestOps = mock[PullRequestOpsTest]
    (pullRequestOps.listPullRequestReviews _)
      .expects(
        validRepoOwner,
        validRepoName,
        validPullRequestNumber,
        sampleToken,
        None)
      .returns(response)

    val ghPullRequests = new GHPullRequests(sampleToken)(pullRequestOps)
    ghPullRequests.listReviews(
      validRepoOwner,
      validRepoName,
      validPullRequestNumber)
  }

  "GHPullRequests.getReview" should "call to PullRequestOps with the right parameters" in {

    val response: Free[GitHub4s, GHResponse[PullRequestReview]] =
      Free.pure(Right(GHResult(pullRequestReview, okStatusCode, Map.empty)))

    val pullRequestOps = mock[PullRequestOpsTest]
    (pullRequestOps.getPullRequestReview _)
      .expects(
        validRepoOwner,
        validRepoName,
        validPullRequestNumber,
        validPullRequestReviewNumber,
        sampleToken)
      .returns(response)

    val ghPullRequests = new GHPullRequests(sampleToken)(pullRequestOps)
    ghPullRequests.getReview(
      validRepoOwner,
      validRepoName,
      validPullRequestNumber,
      validPullRequestReviewNumber)
  }
}
