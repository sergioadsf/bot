//package br.com.sergio.bot;
//
//import java.util.Locale;
//
//import org.cogroo.analyzer.Analyzer;
//import org.cogroo.analyzer.ComponentFactory;
//import org.cogroo.text.Document;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BotCogroo {
//
//	private Analyzer cogroo;
//
//	private BotCogroo() {
//		ComponentFactory factory = ComponentFactory.create(new Locale("pt", "BR"));
//
//		cogroo = factory.createPipe();
//	}
//
//	public void analyze(Document document) {
//		cogroo.analyze(document);
//	}
//
//}
