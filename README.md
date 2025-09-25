# `java.lang.UnsatisfiedLinkError`


1. Removing `testOptions.unitTests.isIncludeAndroidResources = true` seems to make the issue go away on this simple test
2. `forkEvery = 1` makes the tests classes run in isolation, and prevent the errors.

---

```bash
./gradlew test verifyPaparazzi
```

```
RobolectricTest > test FAILED
    java.lang.UnsatisfiedLinkError at SystemProperties.java:-2


'void android.os.SystemProperties.$$robo$$native_set$nativeBinding(java.lang.String, java.lang.String)'
java.lang.UnsatisfiedLinkError: 'void android.os.SystemProperties.$$robo$$native_set$nativeBinding(java.lang.String, java.lang.String)'
	at android.os.SystemProperties.$$robo$$native_set$nativeBinding(Native Method)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor$NativeAccessor.invoke0(Native Method)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor$NativeAccessor.invoke(Unknown Source)
	at java.base/java.lang.reflect.Method.invoke(Unknown Source)
	at org.robolectric.util.ReflectionHelpers.callStaticMethod(ReflectionHelpers.java:440)
	at org.robolectric.util.ReflectionHelpers.callStaticMethod(ReflectionHelpers.java:414)
	at org.robolectric.nativeruntime.DefaultNativeRuntimeLoader.setNativeSystemProperty(DefaultNativeRuntimeLoader.java:457)
	at org.robolectric.nativeruntime.DefaultNativeRuntimeLoader.lambda$ensureLoaded$0(DefaultNativeRuntimeLoader.java:224)
	at org.robolectric.util.PerfStatsCollector.measure(PerfStatsCollector.java:83)
	at org.robolectric.nativeruntime.DefaultNativeRuntimeLoader.ensureLoaded(DefaultNativeRuntimeLoader.java:197)
	at org.robolectric.nativeruntime.DefaultNativeRuntimeLoader.injectAndLoad(DefaultNativeRuntimeLoader.java:157)
	at org.robolectric.android.internal.AndroidTestEnvironment.setUpApplicationState(AndroidTestEnvironment.java:149)
	at org.robolectric.RobolectricTestRunner.beforeTest(RobolectricTestRunner.java:309)
	at org.robolectric.internal.SandboxTestRunner.executeInSandbox(SandboxTestRunner.java:489)
	at org.robolectric.internal.SandboxTestRunner.access$900(SandboxTestRunner.java:67)
	at org.robolectric.internal.SandboxTestRunner$7.evaluate(SandboxTestRunner.java:442)
	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
	at org.robolectric.internal.SandboxTestRunner.access$600(SandboxTestRunner.java:67)
	at org.robolectric.internal.SandboxTestRunner$6.evaluate(SandboxTestRunner.java:333)
	at org.robolectric.internal.SandboxTestRunner$3.evaluate(SandboxTestRunner.java:233)
	at org.robolectric.internal.SandboxTestRunner$5.lambda$evaluate$0(SandboxTestRunner.java:317)
	at org.robolectric.internal.bytecode.Sandbox.lambda$runOnMainThread$0(Sandbox.java:101)
	at java.base/java.util.concurrent.FutureTask.run(Unknown Source)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.base/java.lang.Thread.run(Unknown Source)
```

If you rename `PaparazziTest.kt` to `ZPaparazziTest.kt`, the Paparazzi test will be executed after the Robolectric test, and a different error will appear:

```
ZPaparazziTest > test FAILED
    java.lang.IllegalStateException at Renderer.kt:97


WARNING: A Java agent has been loaded dynamically (/Users/simon.marquis/.gradle/caches/8.13/transforms/bf0671565d5b7bd96acb44eb4c0a3af9/transformed/byte-buddy-agent-1.17.6.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
Sep 25, 2025 4:29:47 PM app.cash.paparazzi.internal.PaparazziLogger logAndroidFramework
INFO: JNIHelp [6]: java.lang.NoSuchMethodError: Method 'long android.animation.PropertyValuesHolder.$$robo$$nGetIntMethod$nativeBinding(java.lang.Class, java.lang.String)' name or signature does not match
Sep 25, 2025 4:29:47 PM app.cash.paparazzi.internal.PaparazziLogger logAndroidFramework
INFO: JNIHelp [6]: RegisterNatives failed for 'android/animation/PropertyValuesHolder'; aborting...
Sep 25, 2025 4:29:47 PM app.cash.paparazzi.internal.PaparazziLogger logAndroidFramework
INFO: JNIHelp [3]: Discarding pending exception (java.lang.NoSuchMethodError: Method 'long android.animation.PropertyValuesHolder.$$robo$$nGetIntMethod$nativeBinding(java.lang.Class, java.lang.String)' name or signature does not match) to throw java/lang/RuntimeException
Sep 25, 2025 4:29:47 PM app.cash.paparazzi.internal.PaparazziLogger logAndroidFramework
INFO: java [4]: Unable to register all android native methods

Sep 25, 2025 4:29:47 PM app.cash.paparazzi.internal.PaparazziLogger error
SEVERE: broken: Native layoutlib failed to load
java.lang.UnsatisfiedLinkError: 'boolean android.util.Log.isLoggable(java.lang.String, int)'
	at android.util.Log.isLoggable(Native Method)
	at android.hardware.input.InputManagerGlobal.<clinit>(InputManagerGlobal.java:80)
	at com.android.layoutlib.bridge.Bridge.setInputManager(Bridge.java:767)
	at java.base/jdk.internal.loader.NativeLibraries.load(Native Method)
	at java.base/jdk.internal.loader.NativeLibraries$NativeLibraryImpl.open(Unknown Source)
	at java.base/jdk.internal.loader.NativeLibraries.loadLibrary(Unknown Source)
	at java.base/jdk.internal.loader.NativeLibraries.loadLibrary(Unknown Source)
	at java.base/java.lang.ClassLoader.loadLibrary(Unknown Source)
	at java.base/java.lang.Runtime.load0(Unknown Source)
	at java.base/java.lang.System.load(Unknown Source)
	at com.android.layoutlib.bridge.Bridge.loadNativeLibraries(Bridge.java:812)
	at com.android.layoutlib.bridge.Bridge.loadNativeLibrariesIfNeeded(Bridge.java:787)
	at com.android.layoutlib.bridge.Bridge.init(Bridge.java:198)
	at app.cash.paparazzi.internal.Renderer.prepare(Renderer.kt:98)
	at app.cash.paparazzi.PaparazziSdk.prepare(PaparazziSdk.kt:126)
	at app.cash.paparazzi.Paparazzi.prepare(Paparazzi.kt:86)
	at app.cash.paparazzi.Paparazzi$apply$1.evaluate(Paparazzi.kt:74)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.runTestClass(JUnitTestClassExecutor.java:112)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:58)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:40)
	at org.gradle.api.internal.tasks.testing.junit.AbstractJUnitTestClassProcessor.processTestClass(AbstractJUnitTestClassProcessor.java:54)
	at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.processTestClass(SuiteTestClassProcessor.java:53)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Unknown Source)
	at java.base/java.lang.reflect.Method.invoke(Unknown Source)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
	at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
	at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:92)
	at jdk.proxy1/jdk.proxy1.$Proxy4.processTestClass(Unknown Source)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker$2.run(TestWorker.java:183)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.executeAndMaintainThreadName(TestWorker.java:132)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:103)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:63)
	at org.gradle.process.internal.worker.child.ActionExecutionWorker.execute(ActionExecutionWorker.java:56)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:121)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:71)
	at worker.org.gradle.process.internal.worker.GradleWorkerMain.run(GradleWorkerMain.java:69)
	at worker.org.gradle.process.internal.worker.GradleWorkerMain.main(GradleWorkerMain.java:74)


Failed to init Bridge.
java.lang.IllegalStateException: Failed to init Bridge.
	at app.cash.paparazzi.internal.Renderer.prepare(Renderer.kt:97)
	at app.cash.paparazzi.PaparazziSdk.prepare(PaparazziSdk.kt:126)
	at app.cash.paparazzi.Paparazzi.prepare(Paparazzi.kt:86)
	at app.cash.paparazzi.Paparazzi$apply$1.evaluate(Paparazzi.kt:74)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.runTestClass(JUnitTestClassExecutor.java:112)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:58)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:40)
	at org.gradle.api.internal.tasks.testing.junit.AbstractJUnitTestClassProcessor.processTestClass(AbstractJUnitTestClassProcessor.java:54)
	at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.processTestClass(SuiteTestClassProcessor.java:53)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Unknown Source)
	at java.base/java.lang.reflect.Method.invoke(Unknown Source)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
	at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
	at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:92)
	at jdk.proxy1/jdk.proxy1.$Proxy4.processTestClass(Unknown Source)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker$2.run(TestWorker.java:183)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.executeAndMaintainThreadName(TestWorker.java:132)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:103)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:63)
	at org.gradle.process.internal.worker.child.ActionExecutionWorker.execute(ActionExecutionWorker.java:56)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:121)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:71)
	at worker.org.gradle.process.internal.worker.GradleWorkerMain.run(GradleWorkerMain.java:69)
	at worker.org.gradle.process.internal.worker.GradleWorkerMain.main(GradleWorkerMain.java:74)
```