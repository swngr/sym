## Azure App Center:

    1. npm install -g appcenter-cli
    2. appcenter login
    3. mvn -DskipTests -P prepare-for-azure-upload package
    4. appcenter test run appium --app "tue1996-gmail.com/test-app-ios" --devices c7b38879 --app-path target/upload/test-classes/apps/ios/HelloWorldiOS.ipa --test-series "master" --locale "en_US" --build-dir target/upload --test-parameter jdk=zulu-11 --test-parameter "test_env=PROPERTIES_FILES=ios.azure.properties"

## BrowserStack:

    1. Upload an app to browserstack dashboard
    2. Get the app URL returned from Browserstack dashboard, go to ios.browserstack.properties, change app.url properties to the new one
    3. Run BrowserStack_iOS_ExampleTest configuration

## AWS Device Farm

    1. Sign in to the Device Farm console at https://console.aws.amazon.com/devicefarm
    2. On the Device Farm navigation panel, choose Mobile Device Testing, then choose Projects.
    3. If you are a new user, choose New project, enter a name for the project, then choose Submit.
    4. If you already have a project, you can choose it to upload your tests to it. 
    5. Open your project, and then choose Create a new run.

## Local

    1. sudo npm install -g appium@next
    2. install xcode, turn on simulator
    3. open terminal, run this command: appium --base-path=/wd/hub
    3. run Local_iOS_ExampleTest configuration
    4. open terminal and run: update-index --assume-unchanged .idea/workspace.xml

## iOS Build

    1. Build should be run on this iOS branch: https://github.com/SymplastLLC/IOS/tree/release/2.73.1/uat

## AppCenter

    1. mvn -DskipTests -P prepare-for-upload package
    2. appcenter test run appium --app "SymplastDev/iOS-Practice-Release" --devices "d3b81459" --app-path target/upload/test-classes/apps/ios/Symplast_Dev.ipa --test-series "master" --locale "en_US" --build-dir target/upload

## WebdriverAgent Setup
    1. https://github.com/appium/appium-xcuitest-driver/blob/master/docs/real-device-config.md
    2. https://github.com/appium/appium-xcuitest-driver/blob/master/docs/real-device-config.md#finding-webdriveragent-project-root-on-the-local-file-system