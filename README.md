# Random User

## Table of contents

- [Features](#features)
- [Download](#download)
- [Setup](#setup)
- [License](#license)

## Features
- **Architecture:** MVVM
- **Kotlin**: v1.9.22
- **Jetpack** Compose
- **Android X:** Start up, splash screen, paging, contraint layout
- **Code format:** Spotless, Ktlint
- **Navigation:** Compose destinations
- **DI:** Hilt
- **Network:** Retrofit, Okhttp, Coroutines, Arrow
- **Logging:** Timber
- **Image loading:** Coil, Lottie
- **Firebase:** Crashlytics, Analytics, App Distribution
- **Maps:** Google Maps
- **Testing:** JUnit5, Mockito,Turbine
- **Performance:** Leakcanary

## Download
- You can download the app from this [link](https://appdistribution.firebase.dev/i/9905ab3d4f5a16ac)

## Setup
- Use Android Studio Jellyfish
- Add keystore file to ```credentials/keystore.jks```
- Add Firebase App Distribution service account json file to ```credentials/sa_app_distribution.json```
- Add Google Services json file to ```app/google-services.json```
- Add this poperties on local.properties
```
# Keystore password
keystore.password=

# Debug key password
keystore.debug.password=

# Debug key alias
keystore.debug.alias=

# Release key password
keystore.release.password=

# Release key alias
keystore.release.alias=
```

## License
```
Copyright 2024 Javi Chaqués

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.apache.org/licenses/LICENSE-2.0
```
