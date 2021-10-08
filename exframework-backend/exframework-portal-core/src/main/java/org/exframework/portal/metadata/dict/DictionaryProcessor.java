//package org.exframework.portal.metadata.dict;
//
//import com.google.auto.service.AutoService;
//import com.squareup.javapoet.JavaFile;
//import com.squareup.javapoet.MethodSpec;
//import com.squareup.javapoet.TypeSpec;
//import org.exframework.portal.metadata.entity.EntityClass;
//import org.exframework.support.common.annotation.Dictionary;
//import org.exframework.support.common.enums.DictionaryEnum;
//
//import javax.annotation.processing.*;
//import javax.lang.model.SourceVersion;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.ElementKind;
//import javax.lang.model.element.Modifier;
//import javax.lang.model.element.TypeElement;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//
///**
// * 自动生成字典实体
// *
// * @author rwe
// * @date 2021/9/8 23:11
// **/
//@AutoService(Processor.class)
//public class DictionaryProcessor extends AbstractProcessor {
//
//    private Filer mFiler;
//
//    @Override
//    public synchronized void init(ProcessingEnvironment processingEnvironment) {
//        super.init(processingEnvironment);
//        mFiler = processingEnvironment.getFiler();
//    }
//
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.latestSupported();
//    }
//
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return Collections.singleton(Dictionary.class.getCanonicalName());
//    }
//
//    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        List<TypeElement> elementList = new ArrayList<>();
//        for (Element element : roundEnv.getElementsAnnotatedWith(Dictionary.class)) {
//            if (element.getKind() == ElementKind.CLASS) {
//                elementList.add((TypeElement) element);
//            }
//        }
//        if(elementList.size() > 0) {
//            MethodSpec spec = MethodSpec.methodBuilder("enums")
//                    .addModifiers(Modifier.PUBLIC)
//                    .returns(Class.class)
////                    .addStatement("$T.out.println($S)", System.class, "hello")
//                    .build();
//            TypeSpec dictionaryClass = TypeSpec.classBuilder("DictionaryAutoGenClass")
//                    .addModifiers(Modifier.PUBLIC)
//                    .addAnnotation(EntityClass.class)
//                    .addMethod(spec)
//                    .build();
//            try {
//                JavaFile javaFile = JavaFile.builder("org.exframework.portal.metadata.dict", dictionaryClass)
//                        .addFileComment("这段代码是自动生成的不要修改！")
//                        .build();
//                javaFile.writeTo(mFiler);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
//}
