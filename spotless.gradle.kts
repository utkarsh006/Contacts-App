ext {
    ci = System.getenv("CI") == "true"
    ktlintVersion = "0.30.0"
    spotlessVersion = "3.13.0"
}

apply<SpotlessPlugin>()

configure<SpotlessExtension> {
    if (ci) {
        ratchetFrom = null
    } else {
        // optional: limit format enforcement to just the files changed by this feature branch
        ratchetFrom("origin/main")
    }


    format("misc") {
        // define the files to apply `misc` to
        target(
            fileTree(
                mapOf(
                    "include" to listOf("**/*.md", "**/.gitignore", "**/*.yaml", "**/*.yml"),
//                    '**/*.gradle', '**/*.md', '**/.gitignore',
                )
            )
        )

        // define the steps to apply to those files
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target(
            fileTree(
                mapOf(
//                    "dir" to ".",
                    "include" to listOf("**/*.kt"),
//                    "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
                )
            )
        )

        val configMap = mapOf(
            "ktlint_standard_package-name" to "disabled",
            "ktlint_standard_no-wildcard-imports" to "disabled",
            "ktlint_standard_no-semi" to "disabled",
            "ktlint_standard_trailing-comma-on-declaration-site" to "disabled",
            "ktlint_standard_trailing-comma-on-call-site" to "disabled"
        )
        ktlint().editorConfigOverride(configMap)
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    java {
        target(
            fileTree(
                mapOf(
                    "include" to listOf("src/*/java/**/*.java")
                )
            )
        )
        googleJavaFormat().aosp()
        removeUnusedImports()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    format("xml") {
        target( // target only xml files in src folders
            fileTree(
                mapOf(
//                    "dir" to ".",
                    "include" to listOf("src/**/*/xml"),
//                    "exclude" to listOf("**/build/**/*.xml")
                )
            )
        )
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
