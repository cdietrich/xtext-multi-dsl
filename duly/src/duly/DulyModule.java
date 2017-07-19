package duly;

import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.serializer.SemanticSequencerExtensions;

@SuppressWarnings("restriction")
public class DulyModule extends DefaultGeneratorModule {
	
	public Class<?extends SemanticSequencerExtensions> bindSemanticSequencerExtensions() {
		return SemanticSequencerExtensions2.class;
	}

}
