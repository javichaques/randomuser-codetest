object BuildConstants {
    const val APPLICATION_ID = "com.javichaques.randomuser"

    const val VERSION_CODE = 10000 // 010000
    const val VERSION_NAME = "1.0.0"

    object Sdk {
        const val MIN_SDK_VERSION = 29
        const val TARGET_SDK_VERSION = 34
        const val COMPILE_SDK_VERSION = 34
    }

    object Development {
        object Firebase {
            const val APP_ID = "1:172920122945:android:e1837ab3a8a7277eeb20c0"
        }

        object Api {
            const val URL = "https://randomuser.me/api/"
        }
    }

    object Production {
        object Firebase {
            const val APP_ID = "1:172920122945:android:b22d4f693fcd0a34eb20c0"
        }

        object Api {
            const val URL = "https://randomuser.me/api/"
        }
    }
}
