plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.safeArgs)
}

android {
    compileSdkVersion(Sdk.compileVersion)

    defaultConfig {
        minSdkVersion(Sdk.minVersion)
        targetSdkVersion(Sdk.targetVersion)

        applicationId = App.id
        versionCode = App.versionCode
        versionName = App.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview"
        )
    }
}

dependencies {
    // Coroutines
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)
    // UI and Appcompat
    implementation(Libs.appcompat)
    implementation(Libs.fragmentKtx)
    implementation(Libs.constraintlayout)
    implementation(Libs.swiperefreshlayout)
    implementation(Libs.recyclerview)
    implementation(Libs.viewpager)
    implementation(Libs.material)
    // ViewModel and LiveData
    implementation(Libs.viewmodelKtx)
    implementation(Libs.livedataKtx)
    implementation(Libs.lifecycleRuntimeKtx)
    // Navigation
    implementation(Libs.navigationKtx)
    implementation(Libs.navigationUiKtx)
    // Room
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)
    // Retrofit
    implementation(Libs.retrofit)
    implementation(Libs.retrofitConverterGson)
    implementation(Libs.loggingInterceptor)
    // Koin
    implementation(Libs.koinViewmodel)
    // Glide
    implementation(Libs.glide)
    kapt(Libs.glideCompiler)
    // PhotoView
    implementation(Libs.photoview)
    // Timber
    implementation(Libs.timber)

    // Unit Tests
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.kotestAssertions)
    testImplementation(TestLibs.androidxTest)
    testImplementation(TestLibs.archTest)
    testImplementation(TestLibs.coroutinesTest)
    testImplementation(TestLibs.robolectric)
}
