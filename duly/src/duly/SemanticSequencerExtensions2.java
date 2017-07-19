package duly;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData.ResourceSetAdapter;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
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
	
	  public Grammar getSuperGrammar(final Grammar grammar) {
		    boolean _isEmpty = grammar.getUsedGrammars().isEmpty();
		    if (_isEmpty) {
		      return null;
		    }
		    Adapter _existingAdapter = EcoreUtil.getExistingAdapter(grammar, SemanticSequencerExtensions.SuperGrammar.class);
		    SemanticSequencerExtensions.SuperGrammar sg = ((SemanticSequencerExtensions.SuperGrammar) _existingAdapter);
		    if ((sg != null)) {
		      return sg.getGrammar();
		    }
		    final URI uri = IterableExtensions.<Grammar>head(grammar.getUsedGrammars()).eResource().getURI();
		    final Resource resource = this.cloneResourceSet(grammar.eResource().getResourceSet()).getResource(uri, true);
		   XtextResourceSet orig = (XtextResourceSet)grammar.eResource().getResourceSet();
		   System.err.println(orig.getNormalizationMap()); 
		   for (Resource rx : grammar.eResource().getResourceSet().getResources()) {
			    	if (rx != grammar.eResource()) {
			    		if (rx.getURI().isFile() || rx.getURI().isArchive() || rx.getURI().isPlatform())
			    		resource.getResourceSet().getResource(orig.getNormalizationMap().get(rx.getURI()), true);
			    	}
		    }
		    EObject _head = IterableExtensions.<EObject>head(resource.getContents());
		    final Grammar result = ((Grammar) _head);
		    EList<Adapter> _eAdapters = grammar.eAdapters();
		    SemanticSequencerExtensions.SuperGrammar _superGrammar = new SemanticSequencerExtensions.SuperGrammar(result);
		    _eAdapters.add(_superGrammar);
		    return result;
		  }

}
