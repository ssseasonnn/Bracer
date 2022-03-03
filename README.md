![](bracer.png)

[![](https://jitpack.io/v/ssseasonnn/Bracer.svg)](https://jitpack.io/#ssseasonnn/Bracer)

# Bracer

Pass parameters safely and quickly between activities or fragments.

*Read this in other languages: [中文](README.zh.md), [English](README.md), [Change Log](CHANGELOG.md)*

## Prepare

1. Add the JitPack repository to your build file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency

```gradle
dependencies {
	implementation 'com.github.ssseasonnn:Bracer:1.0.3'
}
```

## First Blood

Dear, it's all 2020. Are you still writing such code?

```kotlin
val param1 = intent.getStringExtra("param1")
//param1 may be null, so we have to determine null
if (param1 != null) {
    //using param1
}
```
Or:

```kotlin
class ActivityB : AppCompatActivity() {
    private fun gotoActivityA() {
        val intent = Intent(this, ActivityA::class.java)
        intent.putExtra("key_1", "123")
        startActivity(intent)
    }
}

class ActivityA : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //oh shit, wtf!! 写错了key的名字，导致一直获取不到值
        val valueA = intent.getStringExtra("key_l")
        
    }
}
```

more than this:

```kotlin
//Oh my god! Does every Fragment need to be written like this?
class FragmentA : Fragment() {
    var a: String = ""
    var b: String = ""

    companion object {
        fun newFragment(a: String, b: String): FragmentA {
            val fragmentA = FragmentA()
            val bundle = Bundle()
            bundle.putString("key_a", a)
            bundle.putString("key_b", b)
            fragmentA.arguments = bundle
            return fragmentA
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            a = it.getString("key_a") ?: ""
            b = it.getString("key_b") ?: ""
        }
    }
}
```

I just want to simply pass a parameter, why should I write so much code? ? ? I am so tired...

Don't be afraid, now you have ** Bracer !! **


## Double kill

Let's see how the new century should pass parameters correctly

Get parameters in Fragment:

```kotlin
class MutableParamsFragment : Fragment() {
    //basic type
    var intParams by mutableParams<Int>()
    var booleanParams by mutableParams<Boolean>()
    var stringParams by mutableParams<String>()

    //Custom type
    var customParams by mutableParams<CustomParams1>()

    //list
    var intListParams by mutableParams<ArrayList<Int>>()
    var stringListParams by mutableParams<ArrayList<String>>()
    
    //array
    var intArrayParams by mutableParams<IntArray>()
    var arrayCustomParams by mutableParams<Array<CustomParams1>>()

    //Any other type
    //...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Use directly, no need to manually read from Arguments
        println(intParams)
        println(booleanParams)
        println(stringParams)
        println(customParams)
        println(intListParams)
        println(stringListParams)
        println(intArrayParams)

    }
}
```

As you can see, getting parameters from Fragment is so simple and very safe! !!

And supports almost all types!

You will no longer encounter null pointer null, all parameters will have default values;
You won't encounter wrong key writing. All parameters use their own names as keys by default.

> Equivalent to:
> val byteParams = arguments.getByte("byteParams", 0)
> var stringParams = arguments.getString("stringParams") ?: ""

Let's see how to pass parameters:

```kotlin
val fragment = MutableParamsFragment().apply {
    intParams = 1  //Just assign
    booleanParams = true
    stringParams = "123"

    customParams = CustomParams1()
    intListParams = arrayListOf(1,2,3)

    intArrayParams = IntArray(2) { it }
}

//show this Fragment
val beginTransaction = supportFragmentManager.beginTransaction()
beginTransaction.add(R.id.frameLayout, fragment, "")
beginTransaction.commit()

```

Amazing!! Yes, it is so magical, passing parameters is as simple as that!

## Double Kill

Let ’s take a look at Activity

```kotlin
class MutableParamsActivity : AppCompatActivity() {
    //basic type
    var intParams by mutableParams<Int>()
    var booleanParams by mutableParams<Boolean>()
    var stringParams by mutableParams<String>()

    //Custom type
    var customParams by mutableParams<CustomParams1>()

    //list
    var intListParams by mutableParams<ArrayList<Int>>()
    var stringListParams by mutableParams<ArrayList<String>>()
    
    //array
    var intArrayParams by mutableParams<IntArray>()
    var arrayCustomParams by mutableParams<Array<CustomParams1>>()

    //Any other type
    //...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //Use it directly, no need to manually read from Intent anymore
        println(intParams)
        println(booleanParams)
        println(stringParams)
        println(customParams)
        println(intListParams)
        println(stringListParams)
        println(intArrayParams)

    }
}
```

Similar to Fragment, getting parameters from Activity is still so simple and still very safe! !!
You will no longer encounter null pointer null, all parameters will have default values;
You won't encounter wrong key writing. All parameters use their own names as keys by default.

> Equivalent to:
> val byteParams = intent.getByteExtra("byteParams", 0)
> var stringParams = intent.getStringExtra("stringParams") ?: ""

Let's see how to pass parameters:

```kotlin
MutableParamsActivity().apply {
    intParams = 1  //Just assign
    booleanParams = true
    stringParams = "123"

    customParams = CustomParams1()
    intListParams = arrayListOf(1,2,3)

    intArrayParams = IntArray(2) { it }
}.start(context)
```

? ? ? Are you sure you wrote correctly? Why can we create an Activity? ? ?

Yes, it ’s so amazing, it is almost the same as Fragment usage, just a little bit of a show!


## Triple Kill

In addition, Bracer also supports some other features.

For example a custom key:

```kotlin
var customKeyParams by mutableParams<Byte>("this is custom key")
```

Or support custom defaults:

```kotlin
var defaultParams by mutableParams<BigDecimal>(defaultValue = BigDecimal.ONE)
```

## Radiant wins, GG

proguard

```kotlin
-dontwarn androidx.**
-keep class androidx.** { *; }
-keep interface androidx.** { *; }
```

## License

> ```
> Copyright 2019 Season.Zlc
>
> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at
>
>    http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
> ```
