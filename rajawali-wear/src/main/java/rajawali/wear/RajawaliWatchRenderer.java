package rajawali.wear;

import android.content.Context;

import rajawali.Capabilities;
import rajawali.materials.MaterialManager;
import rajawali.materials.textures.TextureManager;
import rajawali.renderer.RajawaliRenderer;

public class RajawaliWatchRenderer extends RajawaliRenderer {

    public RajawaliWatchRenderer(Context context) {
        super(context);
    }

    void create() {
        Capabilities.getInstance();

        mGLES_Major_Version = 2;
        mGLES_Minor_Version = 1;
        supportsUIntBuffers = false;

        mTextureManager = TextureManager.getInstance();
        mTextureManager.setContext(getContext());
        mTextureManager.registerRenderer(this);

        mMaterialManager = MaterialManager.getInstance();
        mMaterialManager.setContext(getContext());
        mMaterialManager.registerRenderer(this);
    }

}
