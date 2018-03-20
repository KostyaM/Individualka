package kocmuk; /**
 * Copyright 2006-2016 Tom Misawa(riversun.org@gmail.com)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * LinearLayout like component for Pure java GUI component like AWT/Swing<br>
 * <br>
 * You can put the components of a pure java as Android's LinearLayout-like
 * style.<br>
 *
 * @author Tom Misawa (riversun.org@gmail.com)
 */
@SuppressWarnings("serial")
public class JLinearLayout extends Component {

    public enum Orientation {
        HORIZONTAL, VERTICAL,
    }

    private JPanel mBasePanel = new JPanel();

    private Orientation mOrientation = Orientation.VERTICAL;
    private List<ComponentHolder> mChildViewList = new ArrayList<ComponentHolder>();

    private class ComponentHolder {
        public Component component;
        public double weight;
    }

    public JLinearLayout() {
        mBasePanel.setBackground(Color.white);
        mBasePanel.setOpaque(true);
    }

    public JLinearLayout setChildOrientation(Orientation orientation) {
        this.mOrientation = orientation;
        return JLinearLayout.this;
    }

    /**
     * Add view(component) to this LayoutGroup
     *
     * @param component
     * @return
     */
    public JLinearLayout addView(Component component) {
        addView(component, 1.0d);
        return JLinearLayout.this;
    }

    /**
     * Add view(component) to this LayoutGroup
     *
     * @param component
     * @param weight
     * @return
     */
    public JLinearLayout addView(Component component, double weight) {

        final ComponentHolder compontentHolder = new ComponentHolder();
        compontentHolder.component = component;
        compontentHolder.weight = weight;

        mChildViewList.add(compontentHolder);

        return JLinearLayout.this;
    }

    /**
     * Set visible
     *
     * @param visible
     * @return
     */
    public JLinearLayout setVisibility(boolean visible) {
        mBasePanel.setVisible(visible);
        return JLinearLayout.this;
    }

    public JLinearLayout insertToFrame(JFrame frame) {
        frame.add(getAsPanel());
        return JLinearLayout.this;
    }

    /**
     * Get this layout group as a JPanel
     *
     * @return
     */
    public JPanel getAsPanel() {

        final int countOfChildObject = mChildViewList.size();

        final GridBagLayout layout = new GridBagLayout();


        mBasePanel.setLayout(layout);


        for (int i = 0; i < countOfChildObject; i++) {

            ComponentHolder childComponentHolder = mChildViewList.get(i);
            final Component childComponent = childComponentHolder.component;
            final double childComponentWeight = childComponentHolder.weight;

            final GridBagConstraints gbc = new GridBagConstraints();

            if (mOrientation == Orientation.VERTICAL) {
                gbc.gridx = 0;
                gbc.gridy = i;
                gbc.weightx = 1.0d;
                gbc.weighty = childComponentWeight;
            } else {
                gbc.gridx = i;
                gbc.gridy = 0;
                gbc.weightx = childComponentWeight;
                gbc.weighty = 1.0d;
            }

            gbc.gridwidth = 1;
            gbc.gridheight = 1;

            gbc.fill = GridBagConstraints.BOTH;

            if (!(childComponent instanceof JLinearLayout)) {
                // If child component is Swing or AWT component
                layout.setConstraints(childComponent, gbc);
                mBasePanel.add(childComponent);

            } else {
                // If child component is JLayoutGroup

                final JLinearLayout childLayoutGroup = (JLinearLayout) childComponent;

                gbc.gridwidth = 1;
                gbc.gridheight = 1;
                gbc.weightx = 1.0d;
                gbc.weighty = 1.0d;

                if (mOrientation == Orientation.VERTICAL) {
                    gbc.weighty = childComponentWeight;// childLayoutGroup.mWeight;
                } else {
                    gbc.weightx = childComponentWeight;// childLayoutGroup.mWeight;
                }

                // Set weight to the panel that becomes base of the panel
                layout.setConstraints(childLayoutGroup.mBasePanel, gbc);

                JPanel childPanel = childLayoutGroup.getAsPanel();

                mBasePanel.add(childPanel);
            }
        }
        return mBasePanel;
    }

}