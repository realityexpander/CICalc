# CICalc
Calculator app for Bitrise project

Sample Android app created to test BitRise CI/CD functionality

## Dashboards

Bitrise: https://app.bitrise.io/dashboard

Firebase (App Distribution): https://console.firebase.google.com/u/0/project/app-distribution-redev/overview

Google Play Console: https://play.google.com/console/u/0/developers/7466162782462237210/app-list

Google Cloud: https://console.cloud.google.com/home/dashboard?project=pc-api-7466162782462237210-872

## Build Pipeline notes for Bitrise

Here are the different build pipelines for this project:

1. **PR** to `dev` branch will trigger a BitRise *UNSIGNED DEBUG* APK build, Run Tests
   and deploy APK to BitRise Only under "Artifacts" tab.

   <i>Note: Any pushes to `dev` branch will trigger a new build.</i> 
2. **Push** to `*.*.*/staging` branch will trigger a BitRise build for an *UNSIGNED DEBUG* APK
   and deploy APK to Firebase App Distribution testers
3. **PR** from `*.*.*/release` to `main` branch will trigger a Bitrise build for a *SIGNED RELEASE* AAB
   and deploy to Play Store Production track

Note: `staging` Variant is not used in this project.
(even though we are using a `staging`branch to deploy to Firebase App Distribution)

TODO: Use the `staging` variant to create a signed-aab for Firebase App Distribution to testers. 

The `staging` variant uses a staging/UAT backend API endpoint, and the `release` variant uses a production endpoint.

Note: `dev` means `origin/dev` branch, `x.x.x/staging` means `origin/x.x.x/staging` branch, etc.

### Flow for a new feature:
1. Create a new `x.x.x/feature` branch from `dev` branch
2. Create a PR from new `x.x.x/feature` branch to `dev` branch
   (Builds DEBUG APK & tests it on BitRise, w/ artifacts only on BitRise for developers to review)
3. Merge PR to `dev` branch (and possibly delete `x.x.x/feature` branch)

### Flow for a new test release:
1. On `dev`, modify `release-notes.txt` and `whatsnew` folder with release notes for the x.x.x release.
2. Create a new `x.x.x/staging` branch from `dev` branch
3. Push `x.x.x/staging` branch to trigger a BitRise build for an *UNSIGNED DEBUG* APK
   (Builds DEBUG APK on BitRise and sent to Firebase App Distribution testers)
4. Switch back to `dev` to make changes. Until ready for another `staging` build.
5. Switch to `x.x.x/staging`
6. Select `Merge 'dev' into 'x.x.x/staging'` (from git menu), & Push `x.x.x/staging` branch.
   (Builds DEBUG APK on BitRise and sent to Firebase App Distribution testers)
7. Repeat steps 4-6 until ready to release

### Flow for a new `Internal App Sharing` release:
1. Log in to developer [console: https://play.google.com/console](https://play.google.com/console/u/2/developers/7466162782462237210/app/4975920456690565880/releases/internal-app-sharing?tab=internalSharing)
2. Build an APK like normal.
3. Upload APK to [here: https://play.google.com/console/u/2/internal-app-sharing](https://play.google.com/console/u/2/internal-app-sharing?pli=1)
4. Users must turn on developer mode (Settings > About phone > tap Build number 7x) to install the app.
5. Users must turn on `Internal App Sharing` in their Developer settings to install the app.
6. Users must be on the email list to download the app.
7. Users can download the app from the link in the email.


### Flow for a new production release:
1. Bump the `versionName` in `build.gradle` file to match the `x.x.x` version of the `x.x.x/staging` branch
2. Create a new `x.x.x/release` branch from `dev` branch
3. Create a new PR from `x.x.x/release` to `main` branch
   (Builds *SIGNED RELEASE* AAB on BitRise and deploys to Play Store production track, puts under review)
4. Merge PR to `main` branch (and possibly delete `x.x.x/staging` branch)

Notes:
- `dev` branch is the development branch for this repo
- `main` branch is the production branch for this repo
- `x.x.x/staging` branch is the staging branch for this repo (for testing releases)
- `x.x.x/release` branch is the release branch for this repo (for production releases)
- `x.x.x/feature/XXXXXX` branch is the feature branch for this repo (for new features)
  - /feature/XXXXXX is the Github Issue # or Notion task # for the feature
  - /fix/XXXXXX is the Github Issue # or Notion task # for the bug fix
  - /hotfix/XXXXXX is the Github Issue # or Notion task # for the hotfix
  - /chore/XXXXXX is the Github Issue # or Notion task # for the chore
  - /refactor/XXXXXX is the Github Issue # or Notion task # for the refactor
  - /test/XXXXXX is the Github Issue # or Notion task # for the test
  - /docs/XXXXXX is the Github Issue # or Notion task # for the docs
  - /misc/XXXXXX is the Github Issue # or Notion task # for the misc
- `x.x.x` is the version number for this repo (e.g. `1.0.0`)


- The `Build Number` on BitRise is the same as `Version Code` in Gradle which is the same 
as `Latest Version` in Play Store.
- The `Release` in Play Store is the same as `Version Name` on BitRise/Gradle.
- The `Version Code` in Play Store is the same as `Build Number` on BitRise.
- Play Store Release Summary `Version` is BitRise `Build Number` + Gradle `Version Name` e.g. 76(1.0.2)


## Build Locally
1. run `brew install bitrise`
2. make a clean clone of repo:
3. `take ~/dev/cicalc-clean-clone`
4. `git clone https://github.com/realityexpander/cicalc.git`
5. Get the `bitrise.yml` file from BitRise and put it in the root of the repo
6. `bitrise setup` to setup BitRise CLI & associated tools
7. `bitrise workflows` to list available workflows
6. `bitrise run <Workflow>` to run primary workflow

## Sample Script for BitRise
```
echo "Application ID:"
envman run bash -c 'echo "Environment test: $APPLICATION_ID"'
tree app/build
```

