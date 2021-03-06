/*
 * Copyright (c) 2018 Nam Nguyen, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package toro.demo.mopub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import im.ene.toro.PlayerSelector;
import im.ene.toro.ToroUtil;
import im.ene.toro.widget.Container;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class DemoListActivity extends AppCompatActivity {

  Container container;
  DemoListAdapter adapter;
  RecyclerView.LayoutManager layoutManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo_list);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    container = findViewById(R.id.container);
    layoutManager = new LinearLayoutManager(this);
    // See DemoListAdapter for detail usage.
    adapter = new DemoListAdapter(PlayerSelector.DEFAULT);
    container.setPlayerSelector(adapter);
    container.setCacheManager(adapter);

    container.setLayoutManager(layoutManager);
    container.setAdapter(adapter);
    container.setPlayerDispatcher(__ -> 500); // The playback will be delayed 500ms.

    // Only when you use Container inside a CoordinatorLayout and depends on Behavior.
    ToroUtil.wrapParamBehavior(container,
        () -> container.onScrollStateChanged(SCROLL_STATE_IDLE));
  }
}
