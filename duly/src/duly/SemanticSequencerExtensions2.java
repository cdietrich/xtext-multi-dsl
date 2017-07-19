package duly;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData.ResourceSetAdapter;
import org.eclipse.xtext.xtext.generator.serializer.SemanticSequencerExtensions;

@SuppressWarnings("restriction")
public class SemanticSequencerExtensions2 extends SemanticSequencerExtensions {
	
	@Override
	protected ResourceSet cloneResourceSet(ResourceSet rs) {
		// TODO Auto-generated method stub
		ResourceSet cloneResourceSet = super.cloneResourceSet(rs);
		ResourceDescriptionsData data = ResourceSetAdapter.findResourceDescriptionsData(rs);
		if (data != null) {
			ResourceSetAdapter.installResourceDescriptionsData(cloneResourceSet, data);
		}
		if (cloneResourceSet instanceof XtextResourceSet && rs instanceof XtextResourceSet) {
			Map<URI, URI> normalizationMap = ((XtextResourceSet)rs).getNormalizationMap();
//			for (String e : normalizationMap.entrySet()) {
//				((XtextResourceSet)cloneResourceSet).c
//			}
//			getNormalizationMap().putAll(normalizationMap);
		}
		return cloneResourceSet;
	}

}
