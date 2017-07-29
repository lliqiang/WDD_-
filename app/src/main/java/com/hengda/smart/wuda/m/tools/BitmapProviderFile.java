package com.hengda.smart.wuda.m.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.qozix.tileview.graphics.BitmapProvider;
import com.qozix.tileview.tiles.Tile;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by lenovo on 2017/4/1.
 */

public class BitmapProviderFile implements BitmapProvider {
    public Bitmap getBitmap( Tile tile, Context context ) {
        Object data = tile.getData();
        if( data instanceof String ) {
            String unformattedFileName = (String) tile.getData();
            String formattedFileName = String.format( unformattedFileName, tile.getColumn(), tile.getRow() );
            try {
                return Picasso.with( context ).load( formattedFileName ).memoryPolicy( MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE ).get();
            } catch( Throwable t ) {
                // probably couldn't find the file, maybe OOME
            }
        }
        return null;
    }





//    private static final BitmapFactory.Options OPTIONS = new BitmapFactory.Options();
//
//    static {
//        OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;
//    }
//
//    @Override
//    public Bitmap getBitmap(Tile tile, Context context) {
//        Object data = tile.getData();
//        if (data instanceof String) {
//            String unformattedFileName = (String) tile.getData();
//            String formattedFileName = String.format(unformattedFileName, tile.getColumn(), tile
//                    .getRow());
//            Bitmap bm = BitmapFactory.decodeFile(formattedFileName, OPTIONS);
//            if (bm != null) {
//                try {
//                    return bm;
//                } catch (OutOfMemoryError oom) {
//                    // oom - you can try sleeping (this method won't be called in the UI thread) or
//                    // try again (or give up)
//                } catch (Exception e) {
//                    // unknown error decoding bitmap
//                }
//            }
//        }
//        return null;
//    }
}
