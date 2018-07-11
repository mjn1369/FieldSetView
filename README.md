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
            app:fsv_legendFont="lobster.ttf"          <!-- Path to your font in Assets folder -->
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
 | ```app:fsv_borderColor```|Border's Color|```#212121```|
 | ```app:fsv_borderWidth```|Border's Width|```2px```|
 | ```app:fsv_borderRadius```|Border's Radius|```0px```|
 | ```app:fsv_borderAlpha```|Border's Alpha|```1```|
 |```app:fsv_legend```|FieldSetView's Legend Text|```""```|
 |```app:fsv_legendSize```|Legend's Text Size|```12sp```|
 |```app:fsv_legendColor```|Legend's Text Color|```#212121```|
 |```app:fsv_legendFont```|Legend's Text Font||
 |```app:fsv_legendIcon```|FieldsetView's Icon||
 |```fsv_legendIconTint```|Icon's Tint|```app:fsv_legend```'s Text Color|
 
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
