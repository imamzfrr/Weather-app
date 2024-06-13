package zfr.mobile.persuhuanduniawii;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private final int spacing;

    public CardSpacingItemDecoration(Context context, int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.right = spacing;

        // Adjust top, bottom, left as needed
        // outRect.top = spacing;
        // outRect.left = spacing;
        // outRect.bottom = spacing;
    }
}
