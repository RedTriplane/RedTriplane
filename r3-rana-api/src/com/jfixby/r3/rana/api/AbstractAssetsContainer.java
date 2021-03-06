
package com.jfixby.r3.rana.api;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.names.ID;

public interface AbstractAssetsContainer {

	Collection<ID> listAssets ();

	Asset getAsset (ID asset_id);

	void printAll ();

// public AssetsContainerOwner getOwner ();

// long getPackageTimeStamp ();
//
// long readPackageTimeStamp ();
//
// boolean purgeAssets (Collection<ID> relatedAssets);

}
