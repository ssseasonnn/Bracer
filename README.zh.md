![](bracer.png)

[![](https://jitpack.io/v/ssseasonnn/Bracer.svg)](https://jitpack.io/#ssseasonnn/Bracer)

# Bracer

一个让你在Activity或Fragment之间优雅传递和解析参数的工具

*Read this in other languages: [中文](README.zh.md), [English](README.md), [Change Log](CHANGELOG.md)*

![](usage.png)

## Prepare

### 1. Add the JitPack repository to your build file
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### 2. Add the dependency

```gradle
dependencies {
	implementation 'com.github.ssseasonnn:Bracer:1.0.6'
}
```

## Usage

### 1. 在Activity中使用

```kotlin
class DemoActivity : AppCompatActivity() {
    //定义参数
    var intParam by mutableParams<Int>()
    var booleanParam by mutableParams<Boolean>()
    var stringParam by mutableParams<String>()
    var customParam by mutableParams<CustomParams1>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //使用参数
        println(intParams)
        println(booleanParams)
        println(stringParams)
        println(customParams)
    }
}
```

传递参数给Activity：

```kotlin
binding.button.setOnClickListener {
    startActivity<DemoActivity> {
        intParam = 1
        booleanParam = true
        stringParam = "abc"
        customParam = CustomParams1()
    }
}

//或者使用传统方式
val intent = Intent(context, DemoActivity::class.java)
intent.putExtra("intParam", 1.toByte())
intent.putExtra("booleanParam", true)
intent.putExtra("stringParam", "abc")
startActivity(intent)
```

> 无需担心，Bracer可以和任何三方Router集成，只需要保证参数名称和Intent中的key是对应的即可

### 2. 在Fragment中使用

同样的，在Fragment中使用也很简单

```kotlin
class DemoFragment : Fragment() {
    var intParam by mutableParams<Int>()
    var booleanParam by mutableParams<Boolean>()
    var stringParam by mutableParams<String>()
    var customParam by mutableParams<CustomParams1>()
}
```

传递参数给Fragment

```kotlin
val fragment = DemoFragment().apply {
        intParam = 1
        booleanParam = true
        stringParam = "abc"
        customParam = CustomParams1()
}

supportFragmentManager.beginTransaction().apply {
        add(R.id.frameLayout, fragment)
        commit()
    }
```

### 3. 在ViewModel中获取Activity或者Fragment中的参数

有时候，会遇到在ViewModel中需要获取Activity或者Fragment参数的情景

```kotlin
//使用SavedStateHandle
class DemoViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {
    private val intParam by stateHandle.params<Int>()
    private val booleanParam by stateHandle.params<Boolean>()
    private val stringParam by stateHandle.params<String>()
    private val customParam by stateHandle.params<CustomParams1>()
}

//创建ViewModel
class DemoActivity : AppCompatActivity() {
    //定义参数
    var intParam by mutableParams<Int>()
    var booleanParam by mutableParams<Boolean>()
    var stringParam by mutableParams<String>()
    var customParam by mutableParams<CustomParams1>()

    //通过viewModels扩展创建ViewModel
    val viewModel by viewModels<DemoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //使用参数
        println(intParams)
        println(booleanParams)
        println(stringParams)
        println(customParams)
    }
}
```

> 要使用viewModels扩展，需要添加依赖：implementation 'androidx.fragment:fragment-ktx:1.5.1'

### 4. 其他特性

除此之外，Bracer还支持一些其他特性.

例如自定义key：

```kotlin
var customKeyParams by mutableParams<Byte>("this is custom key")
```

或者支持自定义默认值：

```kotlin
var defaultParams by mutableParams<BigDecimal>(defaultValue = BigDecimal.ONE)
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
