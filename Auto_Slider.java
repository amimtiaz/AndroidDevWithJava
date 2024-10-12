// library implementation =>>
 implementation('com.tbuonomo:dotsindicator:5.1.0')

// xml 
 /*
 <com.tbuonomo.viewpagerdotsindicator.DotsIndicator
            android:id="@+id/dotIndicator"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:dotsColor="@color/white"
            app:dotsSize="10dp"
            app:dotsSpacing="3dp"
            app:selectedDotColor="@color/yellow_color"
            android:orientation="horizontal"/>

        <androidx.viewpager2.widget.ViewPager2
            android:id="@+id/viewPagerImageSlider"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingStart="24dp"
            android:paddingEnd="24dp"
            android:paddingBottom="16dp"
            />       
    */



// Slider Adapter

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;

    public SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImageView(sliderItems.get(position));
        if (position==sliderItems.size()){
         viewPager2.post(runnable);
        }

    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView imageView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImageView(SliderItem sliderItem) {
         imageView.setImageResource(sliderItem.getImage());
        }

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();

            }
    };
}

===========================================================================================
===========================================================================================

// Uses In Activity or Fragment
    private void autoSlideImages() {

        ViewPager2 viewPager2 = binding.viewPagerImageSlider; // Use binding to get the reference
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.slider_imgeview));
        sliderItems.add(new SliderItem(R.drawable.slider_2));
        sliderItems.add(new SliderItem(R.drawable.slider_3));
        sliderItems.add(new SliderItem(R.drawable.slider_4));
        sliderItems.add(new SliderItem(R.drawable.slider_imgeview));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);

        // Check if ViewPager2's child exists to prevent NullPointerException
        if (viewPager2.getChildCount() > 0) {
            viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        }

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        binding.dotIndicator.setViewPager2(viewPager2);
        viewPager2.setPageTransformer(compositePageTransformer);

        // Setting up auto-slide using a valid reference of ViewPager2
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        // Define the Runnable properly using the initialized ViewPager2 reference
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager2 != null) { // Ensure ViewPager2 is not null
                    int nextItem = (viewPager2.getCurrentItem() + 1) % sliderItems.size();
                    viewPager2.setCurrentItem(nextItem);
                    sliderHandler.postDelayed(this, 3000);
                }
            }
        };

        // Start the auto-slide
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);

    }
