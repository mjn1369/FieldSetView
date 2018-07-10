[ProjectGithubUrl]:     https://github.com/mjn1369/fieldsetview
[PlatformBadge]:  https://img.shields.io/badge/Platform-Android-blue.svg
[ApiBadge]:       https://img.shields.io/badge/API-10%2B-blue.svg
[ProjectLicenceUrl]:    http://www.apache.org/licenses/LICENSE-2.0
[LicenseBadge]:   https://img.shields.io/badge/License-Apache_v2.0-blue.svg
[JitpackBadge]:   https://jitpack.io/v/mjn1369/fieldsetview.svg
[JitpackUrl]:    https://jitpack.io/#mjn1369/fieldsetview
# FieldSetView
[![Platform][PlatformBadge]][ProjectGithubUrl]
[![Api][ApiBadge]][ProjectGithubUrl]
[![License][LicenseBadge]][ProjectLicenceUrl]
[![JitpackBadge]][JitpackUrl]

```FieldSetView``` is an Android custom view which mimics the "fieldset" tag in HTML.

<img src="https://github.com/mjn1369/FieldSetView/blob/master/Screenshot/screenshot.png" width="450" alt="Screenshot">

## Download
### Gradle:
Add the following to your project level build.gradle:

```
allprojects {
   repositories {
      maven { url "https://jitpack.io" }
   }
}
```

Add this to your app build.gradle:

```
dependencies {
   compile 'com.github.mjn1369:fieldsetview:1.0.0'
}
```

## Usage
Add a ```FieldSetView``` to your layout just like any other ```ViewGroup``` :

```xml
<libs.mjn.fieldset.FieldSetView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:padding="8dp"
            app:fsv_borderAlpha="0.75"
            app:fsv_borderColor="#C2185B"
            app:fsv_borderRadius="12dp"
            app:fsv_borderWidth="3dp"
            app:fsv_legend="Gender"
            app:fsv_legendColor="#C2185B"
            app:fsv_legendFont="lobster.ttf"
            app:fsv_legendIcon="@drawable/ic_gender"
            app:fsv_legendIconTint="#C2185B"
            app:fsv_legendPosition="center"
            app:fsv_legendSize="16sp"
            app:fsv_legendDirection="ltr">

            <!-- Child Views Go Here -->

        </libs.mjn.fieldset.FieldSetView>
```
 
If you want to set the legend __programmatically__:
```java
FieldSetView fsv = (FieldSetView) findViewById(R.id.fsv);
fsv.setLegend("New Legend");
```
## Attributes
|            Attribute            |            Description            |            Default            |
 | ------------------------------- | -------------------------------   | --------------------------    |
 | ```app:fsv_borderColor```| Border's Color|```#212121```|
 | ```app:fsv_borderWidth```| Border's Width|```2px```|
 | setMessage(String)               | sets a message for dialog         |  ""                           |
 | setMessageColor(int)               | sets message's color              |  #212121 (kinda black)        |
 | setIcon(int)                      | sets the dialog's icon            |  "close (X)" icon             |
 | setIconTint(int)                 | sets tint for dialog's icon       |  #1976D2 (kinda blue)         |
 | setIconCallback(PrettyDialogCallback)              | sets dialog's icon callback       |  dismiss dialog               |
 | setIcon(int,int,PrettyDialogCallback)                 | sets icon,icon tint,icon callback |                               |
 | addButton(String,int,int,PrettyDialogCallback)                   | adds button with text,text color,background color and callback|   |
 | setTypeface(Typeface)                  | sets typeface for all texts       |                               |
 | setAnimationEnabled(boolean)       | enables or disables dialog animation| Enabled                     |
 | setGravity(int)       | sets the dialog's gravity (TOP, BOTTOM, CENTER,...)| Gravity.CENTER                     |
 
## License
 ```
Copyright 2018 mjn1369

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 
```  
