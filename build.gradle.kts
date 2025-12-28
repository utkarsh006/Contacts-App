plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.spotless)
}

val ci = System.getenv("CI") == "true"
val ktlintVersion = "0.30.0"
val spotlessVersion = "3.13.0"

spotless {
    if (ci) {
        ratchetFrom(null)
    } else {
        // optional: limit format enforcement to just the files changed by this feature branch
        ratchetFrom("origin/main")
    }

    format("misc") {
        // define the files to apply `misc` to
        target("**/*.gradle", "**/*.md", "**/.gitignore")

        // define the steps to apply to those files
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target("**/*.kt")

        val map = mapOf(
            "ktlint_standard_package-name" to "disabled",
            "ktlint_standard_no-wildcard-imports" to "disabled",
            "ktlint_standard_no-semi" to "disabled",
            "ktlint_standard_trailing-comma-on-declaration-site" to "disabled",
            "ktlint_standard_trailing-comma-on-call-site" to "disabled"
        )
        ktlint()
            .editorConfigOverride(map)
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    java {
        target("src/*/java/**/*.java")
        googleJavaFormat().aosp()
        removeUnusedImports()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    format("xml") {
        target("src/**/*/xml") // target only xml files in src folders
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
