package com.chocfun.aclient.testapp.dagger2;

import com.chocfun.baselib.log.LogHelper;
import com.chocfun.baselib.mvp.BaseMVPPresenter;

public class Dagger2Presenter extends BaseMVPPresenter<Dagger2Contracts.View> implements Dagger2Contracts.Presenter {
    public Dagger2Presenter() {
        LogHelper.i("Create " + Dagger2Presenter.class.getSimpleName());
    }

    @Override
    public void print() {
        LogHelper.w("It's " + Dagger2Presenter.class.getSimpleName() + "\nI can do something!");

        String str = "环境配置\n" +
                "\n" +
                "这里以Gradle配置为例子，实用得是AndroidStudio：\n" +
                "\n" +
                "打开project 的 build.gradle ，添加\n" +
                "dependencies {\n" +
                "        classpath 'com.android.tools.build:gradle:2.2.2'\n" +
                "\n" +
                "        // NOTE: Do not place your application dependencies here; they belong\n" +
                "        // in the individual module build.gradle files\n" +
                "\n" +
                "        //dagger2\n" +
                "        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'\n" +
                "    }\n" +
                "打开 app module 的 build.gradle，添加\n" +
                "apply plugin: 'com.android.application'\n" +
                "//dagger2\n" +
                "apply plugin: 'com.neenbedankt.android-apt'\n" +
                "\n" +
                "//...\n" +
                "\n" +
                "dependencies {\n" +
                "    //...\n" +
                "\n" +
                "    //dagger2\n" +
                "    compile 'com.google.dagger:dagger:2.4'\n" +
                "    apt 'com.google.dagger:dagger-compiler:2.4'\n" +
                "    compile 'org.glassfish:javax.annotation:10.0-b28'\n" +
                "}\n" +
                "然后sync gradle一下，环境就配置好了，在实用dagger2的时候，会自动生成一些类，所以最好记一下 build project的快捷键 ctrl+F9，写好dagger代码，然后build一下，就会自动生成 DaggerXXX 开头的一些类。\n" +
                "入门实例\n" +
                "好了，下面我们来看一个入门实例，实用Dagger2到底是怎么依赖注入的。\n" +
                "\n" +
                "现在又一个Person类（这里为了简单起见），然后MainActivity中又一个成员变量person。\n" +
                "\n" +
                "Person.java\n" +
                "\n" +
                "public class Person {\n" +
                "\n" +
                "    public Person() {\n" +
                "        System.out.println(\"a person created\");\n" +
                "    }\n" +
                "}\n" +
                "MainActivity.java\n" +
                "\n" +
                "public class MainActivity extends AppCompatActivity {\n" +
                "\n" +
                "    Person person;\n" +
                "\n" +
                "    @Override\n" +
                "    protected void onCreate(Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        setContentView(R.layout.activity_main);\n" +
                "\n" +
                "        person = new Person();\n" +
                "    }\n" +
                "}\n" +
                "如果不适用依赖注入，那么我们只能在MainActivity中自己new一个Person对象，然后使用。\n" +
                "\n" +
                "使用依赖注入：\n" +
                "\n" +
                "public class MainActivity extends AppCompatActivity {\n" +
                "\n" +
                "    @Inject\n" +
                "    Person person;\n" +
                "\n" +
                "    @Override\n" +
                "    protected void onCreate(Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        setContentView(R.layout.activity_main);\n" +
                "   }\n" +
                "}\n" +
                "在Person对象上添加一个 @Inject注解，即可自动注入对象。\n" +
                "\n" +
                "那么问题来了，就一个@Inject 注解，系统就会自动给我创建一个对象? 当然不是，这个时候我们需要一个Person类的提供者。估计叫它: MainModule\n" +
                "\n" +
                "MainModule.java\n" +
                "\n" +
                "@Module\n" +
                "public class MainModule {\n" +
                "\n" +
                "    @Provides\n" +
                "    Person providesPerson() {\n" +
                "        System.out.println(\"a person created from MainModule\");\n" +
                "        return new Person();\n" +
                "    }\n" +
                "}\n" +
                "里面两个注解，@Module 和 @Provides，Module标注的对象，你可以把它想象成一个工厂，可以向外提供一些类的对象。那么到底提供什么对象呢？\n" +
                "\n" +
                "@Provides标注的方法就是提供对象的，这种方法一般会返回一个对象实例，例如上面返回一个 Person对象\n" +
                "\n" +
                "那么好了，现在Perso类的提供者也有了，我们是不是可以运行起来了。ctrol+F9 build一下项目，然后运行。发现没有任何输出（如果创建Person对象，会打印消息）。为什么了？\n" +
                "\n" +
                "这个时候需要引入第3个东东，component容器。可以把它想成一个容器， module中产出的东西都放在里面，然后将component与我要注入的MainActivity做关联，MainActivity中需要的person就可以冲 component中去去取出来。\n" +
                "\n" +
                "MainComponent.java\n" +
                "\n" +
                "@Component(modules = {MainModule.class})\n" +
                "public interface MainComponent {\n" +
                "\n" +
                "    void inject(MainActivity mainActivity);\n" +
                "}\n" +
                "看到一个新注入 @Component 表示这个接口是一个容器，并且与 MainModule.class 关联，它生产的东西都在这里。\n" +
                "void inject(MainActivity mainActivity); 表示我怎么和要注入的类关联。这个比较抽象！这个时候我们可以 build 一下项目。\n" +
                "\n" +
                "然后在MainActivity中将component 关联进去：\n" +
                "\n" +
                "    @Override\n" +
                "    protected void onCreate(Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        setContentView(R.layout.activity_main);\n" +
                "\n" +
                "        MainComponent component = DaggerMainComponent.builder()\n" +
                "                .mainModule(new MainModule()).build();\n" +
                "        component.inject(this);\n" +
                "    }\n" +
                "下面就是将 MainActivity和Module通过Component关联起来的代码，那么这个时候系统看到 有一个 @Inject修饰的Person，就知道在这个 MainComponent中去找，并且是有 MainModule 的 Provides修饰的方法提供的。\n" +
                "\n" +
                "MainComponent component = DaggerMainComponent.builder()\n" +
                "    .mainModule(new MainModule()).build();\n" +
                "component.inject(this);\n" +
                "然后 build 项目，运行项目，发现打印：\n" +
                "\n" +
                "person from module\n" +
                "a person created\n" +
                "说明确实系统创建了对象，并且注入到MainActivity中。\n" +
                "\n" +
                "细心的同学会发现，MainComponent, MainModule, MainActivity 都是我们自己创建的，上面还有一个 DaggerMainComponent 是上面鬼？这就是你 build project 的时候，dagger自己为你生成的具体的component类（我们自己定义的是MainComponent接口）。感兴趣的可以直接跟到代码里面去看看。\n" +
                "\n" +
                "好了，上面我们把 DI (Dependency Inject) 最基本的流程走了一遍，用到了几个注解：\n" +
                "\n" +
                "@Inject\n" +
                "@Module\n" +
                "@Component\n" +
                "@Provides\n" +
                "下面来介绍另外几个常用的注解。\n" +
                "\n" +
                "其他注解和情况\n" +
                "如果只有最简单的情况，那么上面的几个注解已经够了，但是其实还有很多情形，我们稍微展示几个\n" +
                "\n" +
                "作者：高永峰\n" +
                "链接：https://www.jianshu.com/p/92f793e76654\n" +
                "來源：简书\n" +
                "简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。";

        LogHelper.i(str);
    }
}
