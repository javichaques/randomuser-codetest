object Credentials {
    private fun getEnv(key: String): String = System.getenv(key) ?: ""

    object Keystore {
        val password: String = getEnv("KEYSTORE_STORE_PASSWORD")

        object Debug {
            val alias = getEnv("KEYSTORE_DEBUG_ALIAS")
            val password = getEnv("KEYSTORE_DEBUG_PASSWORD")
        }

        object Release {
            val alias = getEnv("KEYSTORE_RELEASE_ALIAS")
            val password = getEnv("KEYSTORE_RELEASE_PASSWORD")
        }
    }
}
