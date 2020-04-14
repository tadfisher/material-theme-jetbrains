/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.mallowigi.idea.config.ui;

import com.intellij.CommonBundle;
import com.intellij.application.options.colors.ColorAndFontOptions;
import com.intellij.ide.DataManager;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.options.ex.Settings;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.ColorPanel;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.ListCellRendererWrapper;
import com.intellij.ui.components.labels.LinkLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.config.MTBaseConfig;
import com.mallowigi.idea.config.MTCustomThemeConfigurable;
import com.mallowigi.idea.config.MTFileColorsPage;
import com.mallowigi.idea.config.enums.IndicatorStyles;
import com.mallowigi.idea.config.enums.TabHighlightPositions;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.themes.MTThemeFacade;
import com.mallowigi.idea.themes.MTThemes;
import com.mallowigi.idea.utils.MTUiUtils;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.ResourceBundle;

@SuppressWarnings({"ClassWithTooManyFields",
  "ClassWithTooManyMethods",
  "InstanceVariableMayNotBeInitialized",
  "OverlyComplexClass",
  "Duplicates",
  "FeatureEnvy",
  "MagicNumber",
  "StringConcatenation",
  "FieldCanBeLocal",
  "deprecation",
  "DuplicateStringLiteralInspection",
  "rawtypes",
  "Convert2MethodRef",
  "AnonymousInnerClassMayBeStatic",
  "NonBooleanMethodNameMayNotStartWithQuestion",
  "ConfusingFloatingPointLiteral",
  "unused",
  "PublicMethodNotExposedInInterface",
  "UndesirableClassUsage"
})
public class MTForm implements MTFormUI {
  private SpinnerModel highlightSpinnerModel;
  private SpinnerModel tabsHeightSpinnerModel;
  private SpinnerModel leftTreeIndentModel;
  private SpinnerModel rightTreeIndentModel;
  private SpinnerModel customSidebarHeightModel;
  private SpinnerModel treeFontSizeModel;
  private SpinnerModel tabFontSizeModel;
  private SpinnerModel indicatorThicknessSpinnerModel;

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel content;
  private JComponent settingsSep;
  private JPanel mainSettingsPanel;
  private JLabel selectedThemeLabel;
  private ComboBox<MTThemeFacade> themeComboBox;
  private JCheckBox isContrastModeCheckbox;
  private JCheckBox highContrastCheckbox;
  private JLabel customAccentColorLabel;
  private ColorPanel customAccentColorChooser;
  private JCheckBox overrideAccentCheckbox;
  private LinkLabel fileColorsLink;
  private JComponent advSettingsSep;
  private JTabbedPane tabbedPane1;
  private JPanel tabPanel;
  private JLabel tabsDesc;
  private JCheckBox activeTabHighlightCheckbox;
  private ColorPanel activeTabHighlightColor;
  private JSpinner highlightSpinner;
  private JCheckBox isUpperCaseTabsCheckbox;
  private JSpinner tabHeightSpinner;
  private JLabel positionLabel;
  private ComboBox<TabHighlightPositions> tabHighlightPositionComboBox;
  private JCheckBox tabFontSizeCheckbox;
  private JSpinner tabFontSizeSpinner;
  private JLabel panelDesc;
  private JCheckBox isCompactStatusbarCheckbox;
  private JCheckBox isCompactTablesCheckbox;
  private JCheckBox compactDropdownsCheckbox;
  private JCheckBox isCompactMenusCheckbox;
  private JPanel projectViewPanel;
  private JLabel projectViewDesc;
  private JCheckBox isCompactSidebarCheckbox;
  private JSpinner customSidebarSpinner;
  private JCheckBox customTreeIndentCheckbox;
  private JLabel leftLabel;
  private JSpinner leftIndentSpinner;
  private JLabel rightLabel;
  private JSpinner rightSpinner;
  private JLabel selectedIndicatorLabel;
  private ComboBox<IndicatorStyles> indicatorStyleComboBox;
  private JSpinner indicatorThicknessSpinner;
  private JCheckBox styledDirectoriesCheckbox;
  private LinkLabel directoriesColorLink;
  private JCheckBox fontSizeCheckbox;
  private JSpinner fontSizeSpinner;
  private JPanel componentsPanel;
  private JLabel componentDesc;
  private JCheckBox upperCaseButtonsCheckbox;
  private JCheckBox accentScrollbarsCheckbox;
  private LinkLabel scrollbarsLink;
  private JCheckBox themedScrollbarsCheckbox;
  private JCheckBox accentModeCheckbox;
  private JLabel secondAccentLabel;
  private ColorPanel secondAccentColorChooser;
  private JCheckBox tabShadowCheckbox;
  private JPanel featuresPanel;
  private JLabel featuresDesc;
  private JCheckBox useMaterialFontCheckbox;
  private JCheckBox fileColorsCheckbox;
  private LinkLabel fileStatusColorsLink;
  private JCheckBox useMaterialWallpapersCheckbox;
  private JPanel otherTweaksPanel;
  private JLabel tweaksDesc;
  private JCheckBox isThemeInStatusCheckbox;
  private JCheckBox darkTitleBarCheckbox;
  private JCheckBox codeAdditionsCheckBox;
  private JCheckBox isColoredOpenedDirsCheckbox;
  private JButton resetDefaultsButton;
  // GEN-END:variables

  public MTForm() {
    initComponents();
  }

  public static void showFontWarningDialog() {
    Messages.showWarningDialog(
      MaterialThemeBundle.message("MTForm.useMaterialFonts.warning.message"),
      MaterialThemeBundle.message("MTForm.useMaterialFonts.warning.title")
    );
  }

  private static void showTitleBarDialog() {
    Messages.showWarningDialog(
      MaterialThemeBundle.message("MTForm.themedTitleBar.warning.message"),
      MaterialThemeBundle.message("MTForm.themedTitleBar.warning.title")
    );
  }

  @Override
  public final void init() {
    final MTConfig config = MTConfig.getInstance();
    final int highlightThickness = MTUiUtils.valueInRange(config.getHighlightThickness(), MTConfig.MIN_HIGHLIGHT_THICKNESS,
      MTConfig.MAX_HIGHLIGHT_THICKNESS);
    final int tabsHeight = MTUiUtils.valueInRange(config.getTabsHeight(), MTConfig.MIN_TABS_HEIGHT, MTConfig.MAX_TABS_HEIGHT);
    final int rightTreeIndent = MTUiUtils.valueInRange(config.getRightTreeIndent(), MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT);
    final int leftTreeIndent = MTUiUtils.valueInRange(config.getLeftTreeIndent(), MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT);
    final int customSidebarHeight = MTUiUtils.valueInRange(config.getCustomSidebarHeight(), MTConfig.MIN_SIDEBAR_HEIGHT,
      MTConfig.MAX_SIDEBAR_HEIGHT);
    final int treeFontSize = MTUiUtils.valueInRange(config.getTreeFontSize(), MTConfig.MIN_FONT_SIZE, MTConfig.MAX_FONT_SIZE);
    final int tabFontSize = MTUiUtils.valueInRange(config.getTabFontSize(), MTConfig.MIN_FONT_SIZE, MTConfig.MAX_FONT_SIZE);
    final int selectedTabIndex = MTUiUtils.valueInRange(config.getSettingsSelectedTab(), 0, MTConfig.MAX_TAB_INDEX);

    highlightSpinnerModel = new SpinnerNumberModel(highlightThickness, MTConfig.MIN_HIGHLIGHT_THICKNESS,
      MTConfig.MAX_HIGHLIGHT_THICKNESS, 1);
    highlightSpinner.setModel(highlightSpinnerModel);
    tabsHeightSpinnerModel = new SpinnerNumberModel(tabsHeight, MTConfig.MIN_TABS_HEIGHT, MTConfig.MAX_TABS_HEIGHT, 1);
    tabHeightSpinner.setModel(tabsHeightSpinnerModel);
    leftTreeIndentModel = new SpinnerNumberModel(leftTreeIndent, MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT, 2);
    leftIndentSpinner.setModel(leftTreeIndentModel);
    rightTreeIndentModel = new SpinnerNumberModel(rightTreeIndent, MTConfig.MIN_TREE_INDENT, MTConfig.MAX_TREE_INDENT, 2);
    rightSpinner.setModel(rightTreeIndentModel);
    customSidebarHeightModel = new SpinnerNumberModel(customSidebarHeight, MTConfig.MIN_SIDEBAR_HEIGHT, MTConfig.MAX_SIDEBAR_HEIGHT, 2);
    customSidebarSpinner.setModel(customSidebarHeightModel);
    tabFontSizeModel = new SpinnerNumberModel(tabFontSize, MTConfig.MIN_FONT_SIZE, MTConfig.MAX_FONT_SIZE, 1);
    tabFontSizeSpinner.setModel(tabFontSizeModel);
    treeFontSizeModel = new SpinnerNumberModel(treeFontSize, MTConfig.MIN_FONT_SIZE, MTConfig.MAX_FONT_SIZE, 1);
    fontSizeSpinner.setModel(treeFontSizeModel);
    indicatorThicknessSpinnerModel = new SpinnerNumberModel(highlightThickness, MTConfig.MIN_INDICATOR_THICKNESS,
      MTConfig.MAX_INDICATOR_THICKNESS, 1);
    indicatorThicknessSpinner.setModel(indicatorThicknessSpinnerModel);
  }

  @Override
  public final JComponent getContent() {
    return content;
  }

  @Override
  public void dispose() {
  }

  // region ----------- Main Settings --------------

  //region Selected Theme

  @SuppressWarnings("OverlyLongMethod")
  public final void setFormState(final MTBaseConfig config) {
    final MTConfig mtConfig = (MTConfig) config;

    setHighlightPosition(mtConfig.getTabHighlightPosition());
    setCodeAdditionsEnabled(mtConfig.isCodeAdditionsEnabled());
    setCustomAccentColor(ColorUtil.fromHex(mtConfig.getAccentColor()));
    setSecondAccentColor(ColorUtil.fromHex(mtConfig.getSecondAccentColor()));
    setCustomSidebarHeight(mtConfig.getCustomSidebarHeight());
    setHighlightColor(mtConfig.getHighlightColor());
    setHighlightColorEnabled(mtConfig.isHighlightColorEnabled());
    setHighlightThickness(mtConfig.getHighlightThickness());
    setIndicatorStyle(mtConfig.getIndicatorStyle());
    setIndicatorThickness(mtConfig.getIndicatorThickness());
    setIsAccentMode(mtConfig.isAccentMode());
    setIsAccentScrollbars(mtConfig.isAccentScrollbars());
    setIsCompactDropdowns(mtConfig.isCompactDropdowns());
    setIsCompactMenus(mtConfig.isCompactMenus());
    setIsCompactSidebar(mtConfig.isCompactSidebar());
    setIsCompactStatusBar(mtConfig.isCompactStatusBar());
    setIsCompactTables(mtConfig.isCompactTables());
    setIsContrastMode(mtConfig.isContrastMode());
    setIsCustomTreeIndent(mtConfig.isCustomTreeIndentEnabled());
    setIsDarkTitleBar(mtConfig.isDarkTitleBar());
    setIsFileStatusColors(mtConfig.isFileStatusColorsEnabled());
    setIsHighContrast(mtConfig.isHighContrast());
    setIsOverrideAccents(mtConfig.isOverrideAccentColor());
    setIsStatusBarTheme(mtConfig.isStatusBarTheme());
    setIsStyledDirectories(mtConfig.isStyledDirectories());
    setIsTabsShadow(mtConfig.isTabsShadow());
    setIsThemedScrollbars(mtConfig.isThemedScrollbars());
    setIsTabFontSizeEnabled(mtConfig.isTabFontSizeEnabled());
    setIsTreeFontSizeEnabled(mtConfig.isTreeFontSizeEnabled());
    setIsUppercaseButtons(mtConfig.isUpperCaseButtons());
    setIsUpperCaseTabs(mtConfig.isUpperCaseTabs());
    setLeftTreeIndent(mtConfig.getLeftTreeIndent());
    setRightTreeIndent(mtConfig.getRightTreeIndent());
    setSelectedTabIndex(mtConfig.getSettingsSelectedTab());
    setTabsHeight(mtConfig.getTabsHeight());
    setTheme(mtConfig.getSelectedTheme());
    setTabFontSize(mtConfig.getTabFontSize());
    setTreeFontSize(mtConfig.getTreeFontSize());
    setUseMaterialFont(mtConfig.isUseMaterialFont2());
    setUseMaterialWallpapers(mtConfig.isUseMaterialWallpapers());
    setUseColoredDirectories(mtConfig.isUseColoredDirectories());

    afterStateSet();
  }

  @SuppressWarnings({"OverlyComplexMethod",
    "OverlyLongMethod"})
  public final boolean isModified(final MTBaseConfig config) {
    final MTConfig mtConfig = (MTConfig) config;

    boolean modified = mtConfig.isReset();
    modified = modified || mtConfig.isHighlightColorChanged(getHighlightColor());
    modified = modified || mtConfig.isSelectedThemeChanged(getTheme());
    modified = modified || mtConfig.isHighlightColorEnabledChanged(isHighlightColorEnabled());
    modified = modified || mtConfig.isHighlightThicknessChanged(getHighlightThickness());
    modified = modified || mtConfig.isContrastModeChanged(isContrastMode());
    modified = modified || mtConfig.isStyledDirectoriesChanged(isStyledDirectories());
    modified = modified || mtConfig.isTabsHeightChanged(getTabsHeight());
    modified = modified || mtConfig.isTabHighlightPositionChanged(getTabHighlightPosition());
    modified = modified || mtConfig.isAccentModeChanged(isAccentMode());

    modified = modified || mtConfig.isCustomTreeIndentChanged(isCustomTreeIndent());
    modified = modified || mtConfig.isRightTreeIndentChanged(getRightTreeIndent());
    modified = modified || mtConfig.isLeftTreeIndentChanged(getLeftTreeIndent());
    modified = modified || mtConfig.isTabFontSizeChanged(getTabFontSize());
    modified = modified || mtConfig.isTabFontSizeEnabledChanged(isTabFontSizeEnabled());
    modified = modified || mtConfig.isTreeFontSizeChanged(getTreeFontSize());
    modified = modified || mtConfig.isTreeFontSizeEnabledChanged(isTreeFontSizeEnabled());
    modified = modified || mtConfig.isUpperCaseTabsChanged(isUpperCaseTabs());

    modified = modified || mtConfig.isCompactSidebarChanged(isCompactSidebar());
    modified = modified || mtConfig.isCompactStatusBarChanged(isCompactStatusBar());
    modified = modified || mtConfig.isCompactTablesChanged(isCompactTables());
    modified = modified || mtConfig.isCompactMenusChanged(isCompactMenus());

    modified = modified || mtConfig.isStatusBarThemeChanged(isStatusBarTheme());
    modified = modified || mtConfig.isCustomSidebarHeightChanged(getCustomSidebarHeight());

    modified = modified || mtConfig.isThemedScrollbarsChanged(isThemedScrollbars());
    modified = modified || mtConfig.isAccentScrollbarsChanged(isAccentScrollbars());
    modified = modified || mtConfig.isFileStatusColorsEnabledChanged(isFileStatusColors());
    modified = modified || mtConfig.isDarkTitleBarChanged(isDarkTitleBar());

    modified = modified || mtConfig.isAccentColorChanged(getCustomAccentColor());
    modified = modified || mtConfig.isSecondAccentColorChanged(getSecondAccentColor());
    modified = modified || mtConfig.isIndicatorStyleChanged(getIndicatorStyle());
    modified = modified || mtConfig.isIndicatorThicknessChanged(getIndicatorThickness());
    modified = modified || mtConfig.isUseMaterialFontChanged(isUseMaterialFonts());
    modified = modified || mtConfig.isUseMaterialWallpapersChanged(isUseMaterialWallpapers());

    modified = modified || mtConfig.isCompactDropdownsChanged(isCompactDropdowns());
    modified = modified || mtConfig.isUpperCaseButtonsChanged(isUpperCaseButtons());
    modified = modified || mtConfig.isHighContrastChanged(isHighContrast());

    modified = modified || mtConfig.isOverrideAccentColorChanged(isOverrideAccents());
    modified = modified || mtConfig.isTabsShadowChanged(isTabsShadow());
    modified = modified || mtConfig.isCodeAdditionsEnabledChanged(isCodeAdditionsEnabled());
    modified = modified || mtConfig.isUseColoredDirectoriesChanged(isUseColoredDirectories());

    return modified;
  }
  //endregion

  public final MTThemeFacade getTheme() {
    return (MTThemeFacade) themeComboBox.getSelectedItem();
  }

  private void setTheme(final MTThemeFacade selectedTheme) {
    themeComboBox.setSelectedItem(selectedTheme);
  }
  //endregion

  //region Contrast Mode
  public final boolean isContrastMode() {
    return isContrastModeCheckbox.isSelected();
  }

  //region High Contrast
  public final boolean isHighContrast() {
    return highContrastCheckbox.isSelected();
  }
  //endregion

  //region Accent Color
  public final Color getCustomAccentColor() {
    return customAccentColorChooser.getSelectedColor();
  }

  private void setCustomAccentColor(final Color customAccentColor) {
    customAccentColorChooser.setSelectedColor(customAccentColor);
  }
  //endregion

  //region Override accents
  public final boolean isOverrideAccents() {
    return overrideAccentCheckbox.isSelected();
  }

  //region Highlight Color
  public final Color getHighlightColor() {
    return activeTabHighlightColor.getSelectedColor();
  }

  //endregion

  // endregion

  // region ----------- Tab Settings -----------

  private void setHighlightColor(@NotNull final Color highlightColor) {
    activeTabHighlightColor.setSelectedColor(highlightColor);
  }

  //region Highlight color enabled
  public final boolean isHighlightColorEnabled() {
    return activeTabHighlightCheckbox.isSelected();
  }
  //endregion

  private void setHighlightColorEnabled(final boolean enabled) {
    activeTabHighlightCheckbox.setSelected(enabled);
    enableDisableActiveTabColor(enabled);
  }

  //region Thickness
  public final Integer getHighlightThickness() {
    return (Integer) highlightSpinnerModel.getValue();
  }
  //endregion

  private void setHighlightThickness(final Integer highlightThickness) {
    highlightSpinnerModel.setValue(highlightThickness);
  }

  //region Uppercase tabs
  public final boolean isUpperCaseTabs() {
    return isUpperCaseTabsCheckbox.isSelected();
  }
  //endregion

  //region Tabs Height
  public final Integer getTabsHeight() {
    return (Integer) tabsHeightSpinnerModel.getValue();
  }

  private void setTabsHeight(final int tabsHeight) {
    tabsHeightSpinnerModel.setValue(tabsHeight);
  }
  //endregion

  //region Tab Highlight Position
  public final TabHighlightPositions getTabHighlightPosition() {
    return (TabHighlightPositions) tabHighlightPositionComboBox.getSelectedItem();
  }

  //region Tab Font Size
  public final Integer getTabFontSize() {
    return (Integer) tabFontSizeModel.getValue();
  }
  //endregion

  private void setTabFontSize(final int tabFontSize) {
    tabFontSizeModel.setValue(tabFontSize);
  }

  public final boolean isTabFontSizeEnabled() {
    return tabFontSizeCheckbox.isSelected();
  }
  //endregion

  //region Compact Statusbar
  public final boolean isCompactStatusBar() {
    return isCompactStatusbarCheckbox.isSelected();
  }

  //region Compact Tables
  public final boolean isCompactTables() {
    return isCompactTablesCheckbox.isSelected();
  }

  //region Compact Dropdowns
  public final boolean isCompactDropdowns() {
    return compactDropdownsCheckbox.isSelected();
  }

  //region Compact Menus
  public final boolean isCompactMenus() {
    return isCompactMenusCheckbox.isSelected();
  }
  //endregion

  // endregion

  // region ----------- Compact Settings -----------

  //region Compact Sidebar
  public final boolean isCompactSidebar() {
    return isCompactSidebarCheckbox.isSelected();
  }

  //region Custom Sidebar Height
  public final Integer getCustomSidebarHeight() {
    return (Integer) customSidebarHeightModel.getValue();
  }
  //endregion

  private void setCustomSidebarHeight(final Integer customSidebarHeight) {
    customSidebarHeightModel.setValue(customSidebarHeight);
  }

  //region Is Custom Tree Indent
  public final boolean isCustomTreeIndent() {
    return customTreeIndentCheckbox.isSelected();
  }
  //endregion

  //region Custom Tree Indent
  public final Integer getRightTreeIndent() {
    return (Integer) rightTreeIndentModel.getValue();
  }

  private void setRightTreeIndent(final Integer rightTreeIndent) {
    rightTreeIndentModel.setValue(rightTreeIndent);
  }
  //endregion

  public final Integer getLeftTreeIndent() {
    return (Integer) leftTreeIndentModel.getValue();
  }

  private void setLeftTreeIndent(final Integer leftTreeIndent) {
    leftTreeIndentModel.setValue(leftTreeIndent);
  }
  //endregion

  // endregion

  // region ----------- Project View Settings -----------

  public final IndicatorStyles getIndicatorStyle() {
    return (IndicatorStyles) indicatorStyleComboBox.getSelectedItem();
  }

  private void setIndicatorStyle(final IndicatorStyles arrowsStyle) {
    indicatorStyleComboBox.setSelectedItem(arrowsStyle);
  }
  //endregion

  //region Indicator Thickness
  public final Integer getIndicatorThickness() {
    return (Integer) indicatorThicknessSpinnerModel.getValue();
  }

  private void setIndicatorThickness(final Integer indicatorThickness) {
    indicatorThicknessSpinnerModel.setValue(indicatorThickness);
  }
  //endregion

  //region Styled Directories
  public final boolean isStyledDirectories() {
    return styledDirectoriesCheckbox.isSelected();
  }

  //region Tree Font Size
  public final Integer getTreeFontSize() {
    return (Integer) treeFontSizeModel.getValue();
  }
  //endregion

  private void setTreeFontSize(final int treeFontSize) {
    treeFontSizeModel.setValue(treeFontSize);
  }

  public final boolean isTreeFontSizeEnabled() {
    return fontSizeCheckbox.isSelected();
  }

  //region Uppercase buttons
  public final boolean isUpperCaseButtons() {
    return upperCaseButtonsCheckbox.isSelected();
  }

  //region Themed Scrollbars
  public final boolean isThemedScrollbars() {
    return themedScrollbarsCheckbox.isSelected();
  }
  //endregion

  //region Indicator Styles

  //region Accent Scrollbars
  public final boolean isAccentScrollbars() {
    return accentScrollbarsCheckbox.isSelected();
  }

  // region Tabs shadow
  public final boolean isTabsShadow() {
    return tabShadowCheckbox.isSelected();
  }
  //endregion

  //region Accent Mode
  public final boolean isAccentMode() {
    return accentModeCheckbox.isSelected();
  }

  //region Second Accent Color
  public final Color getSecondAccentColor() {
    return secondAccentColorChooser.getSelectedColor();
  }
  //endregion

  private void setSecondAccentColor(final Color secondAccentColor) {
    secondAccentColorChooser.setSelectedColor(secondAccentColor);
  }

  //region Material Fonts
  public final boolean isUseMaterialFonts() {
    return useMaterialFontCheckbox.isSelected();
  }
  //endregion

  //region File Status colors
  public final boolean isFileStatusColors() {
    return fileColorsCheckbox.isSelected();
  }

  //region Use Material Wallpapers
  public final boolean isUseMaterialWallpapers() {
    return useMaterialWallpapersCheckbox.isSelected();
  }

  private void setUseMaterialWallpapers(final boolean useMaterialWallpapers) {
    useMaterialWallpapersCheckbox.setSelected(useMaterialWallpapers);
  }

  //region Status Bar
  public final boolean isStatusBarTheme() {
    return isThemeInStatusCheckbox.isSelected();
  }
  //endregion

  // endregion

  // region ----------- Component Settings ---------

  //region Title Bar
  public final boolean isDarkTitleBar() {
    return darkTitleBarCheckbox.isSelected();
  }

  //region Code Additions enabled
  public final boolean isCodeAdditionsEnabled() {
    return codeAdditionsCheckBox.isSelected();
  }
  //endregion

  private void setCodeAdditionsEnabled(final boolean enabled) {
    codeAdditionsCheckBox.setSelected(enabled);
  }

  //region Colored Directories
  public final boolean isUseColoredDirectories() {
    return isColoredOpenedDirsCheckbox.isSelected();
  }

  //endregion

  private void setUseColoredDirectories(final boolean useColoredDirectories) {
    isColoredOpenedDirsCheckbox.setSelected(useColoredDirectories);
  }

  //region Selected tab
  public final Integer getSelectedTabIndex() {
    return tabbedPane1.getSelectedIndex();
  }

  //endregion

  private void setSelectedTabIndex(final Integer settingsSelectedTab) {
    tabbedPane1.setSelectedIndex(settingsSelectedTab);
  }

  private void afterStateSet() {
  }

  // endregion

  private void setIsContrastMode(final boolean isContrastMode) {
    isContrastModeCheckbox.setSelected(isContrastMode);
  }

  private void setIsHighContrast(final boolean isHighContrast) {
    highContrastCheckbox.setSelected(isHighContrast);
  }

  //endregion

  private void setIsOverrideAccents(final boolean isOverrideAccents) {
    overrideAccentCheckbox.setSelected(isOverrideAccents);
    enableDisableAccentColor(isOverrideAccents);
  }

  private void setIsUpperCaseTabs(final boolean upperCaseTabs) {
    isUpperCaseTabsCheckbox.setSelected(upperCaseTabs);
  }
  //endregion

  // endregion

  // region ----------- Features Settings -----------

  private void setHighlightPosition(final TabHighlightPositions position) {
    tabHighlightPositionComboBox.setSelectedItem(position);
  }

  private void setIsTabFontSizeEnabled(final boolean isTabFontSizeEnabled) {
    tabFontSizeCheckbox.setSelected(isTabFontSizeEnabled);
    enableDisableTabFontSize(isTabFontSizeEnabled);
  }
  //endregion

  private void setIsCompactStatusBar(final boolean compactStatusBar) {
    isCompactStatusbarCheckbox.setSelected(compactStatusBar);
  }

  private void setIsCompactTables(final boolean compactTables) {
    isCompactTablesCheckbox.setSelected(compactTables);
  }

  //endregion

  private void setIsCompactDropdowns(final boolean compactDropdowns) {
    compactDropdownsCheckbox.setSelected(compactDropdowns);
  }

  private void setIsCompactMenus(final boolean compactMenus) {
    isCompactMenusCheckbox.setSelected(compactMenus);
  }
  //endregion

  // endregion

  // region ----------- Other Settings ------------

  private void setIsCompactSidebar(final boolean compactSidebar) {
    isCompactSidebarCheckbox.setSelected(compactSidebar);
    enableDisableCustomSidebarHeight(compactSidebar);
  }

  private void setIsCustomTreeIndent(final boolean isCustomTreeIndent) {
    customTreeIndentCheckbox.setSelected(isCustomTreeIndent);
    enableDisableCustomTreeIndent(isCustomTreeIndent);
  }

  //endregion

  private void setIsStyledDirectories(final boolean isStyled) {
    styledDirectoriesCheckbox.setSelected(isStyled);
  }

  private void setIsTreeFontSizeEnabled(final boolean isTreeFontSizeEnabled) {
    fontSizeCheckbox.setSelected(isTreeFontSizeEnabled);
    enableDisableTreeFontSize(isTreeFontSizeEnabled);
  }

  //endregion

  private void setIsUppercaseButtons(final boolean upperCaseButtons) {
    upperCaseButtonsCheckbox.setSelected(upperCaseButtons);
  }

  private void setIsThemedScrollbars(final boolean isThemedScrollbars) {
    themedScrollbarsCheckbox.setSelected(isThemedScrollbars);
  }
  //endregion

  private void setIsAccentScrollbars(final boolean isAccentScrollbars) {
    accentScrollbarsCheckbox.setSelected(isAccentScrollbars);
  }

  private void setIsTabsShadow(final boolean tabsShadow) {
    tabShadowCheckbox.setSelected(tabsShadow);
  }
  //endregion

  // endregion

  private void setIsAccentMode(final boolean isAccentMode) {
    accentModeCheckbox.setSelected(isAccentMode);
    enableDisableSecondAccentColor(isAccentMode);
  }

  private void setUseMaterialFont(final boolean isUseMaterialFont) {
    useMaterialFontCheckbox.setSelected(isUseMaterialFont);
  }
  //endregion

  //region ~~~~~~~~~~~~ Enabled listeners ~~~~~~~~~~~~~~~~~~

  private void setIsFileStatusColors(final boolean fileStatusColorsEnabled) {
    fileColorsCheckbox.setSelected(fileStatusColorsEnabled);
  }

  private void setIsStatusBarTheme(final boolean statusBarTheme) {
    isThemeInStatusCheckbox.setSelected(statusBarTheme);
  }

  private void setIsDarkTitleBar(final boolean darkTitleBar) {
    darkTitleBarCheckbox.setSelected(darkTitleBar);
  }

  private void enableDisableCustomTreeIndent(final boolean isCustomTreeIndent) {
    leftIndentSpinner.setEnabled(isCustomTreeIndent);
    rightSpinner.setEnabled(isCustomTreeIndent);
  }

  private void enableDisableActiveTabColor(final boolean isCustomTreeIndent) {
    activeTabHighlightColor.setEnabled(isCustomTreeIndent);
  }

  private void enableDisableAccentColor(final boolean overrideAccentColor) {
    customAccentColorChooser.setEnabled(!overrideAccentColor);
  }

  private void enableDisableSecondAccentColor(final boolean accentMode) {
    secondAccentColorChooser.setEnabled(accentMode);
  }

  private void enableDisableCustomSidebarHeight(final boolean isCustomSidebarHeight) {
    customSidebarSpinner.setEnabled(isCustomSidebarHeight);
  }

  private void enableDisableTreeFontSize(final boolean isTreeFontSize) {
    fontSizeSpinner.setEnabled(isTreeFontSize);
  }

  private void enableDisableTabFontSize(final boolean isTabFontSize) {
    tabFontSizeSpinner.setEnabled(isTabFontSize);
  }
  //endregion

  //region Events - Actions Listeners

  private void enableDisableCompactStatusBar(final boolean isMaterialDesign) {
    isCompactStatusbarCheckbox.setEnabled(isMaterialDesign);
  }

  private void enableDisableCompactTableCells(final boolean isMaterialDesign) {
    isCompactTablesCheckbox.setEnabled(isMaterialDesign);
  }

  private void enableDisableDropdownLists(final boolean isMaterialDesign) {
    compactDropdownsCheckbox.setEnabled(isMaterialDesign);
  }

  private void customTreeIndentCheckboxActionPerformed(final ActionEvent e) {
    enableDisableCustomTreeIndent(customTreeIndentCheckbox.isSelected());
  }

  private void activeTabHighlightCheckboxActionPerformed(final ActionEvent e) {
    enableDisableActiveTabColor(activeTabHighlightCheckbox.isSelected());
  }

  private void isCompactSidebarCheckboxActionPerformed(final ActionEvent e) {
    enableDisableCustomSidebarHeight(isCompactSidebarCheckbox.isSelected());
  }

  private void fontSizeCheckboxActionPerformed(final ActionEvent e) {
    enableDisableTreeFontSize(fontSizeCheckbox.isSelected());
  }

  @SuppressWarnings("FeatureEnvy")
  private void isDarkTitleBarActionPerformed(final ActionEvent e) {
    if (SystemInfo.isWin10OrNewer && darkTitleBarCheckbox.isSelected()) {
      final int dialog = Messages.showOkCancelDialog(
        MaterialThemeBundle.message("MTForm.windowsTitleBar.warning.message"),
        MaterialThemeBundle.message("MTForm.windowsTitleBar.warning.title"),
        CommonBundle.getOkButtonText(),
        CommonBundle.getCancelButtonText(),
        Messages.getWarningIcon());

      if (dialog == Messages.CANCEL) {
        darkTitleBarCheckbox.setSelected(false);
      }
    } else if (SystemInfo.isMac && SystemInfo.isJavaVersionAtLeast(11) && darkTitleBarCheckbox.isSelected()) {
      final int dialog = Messages.showOkCancelDialog(
        MaterialThemeBundle.message("MTForm.themedTitleBar.warning.message"),
        MaterialThemeBundle.message("MTForm.themedTitleBar.warning.title"),
        CommonBundle.getOkButtonText(),
        CommonBundle.getCancelButtonText(),
        Messages.getWarningIcon());

      if (dialog == Messages.CANCEL) {
        darkTitleBarCheckbox.setSelected(false);
      }
    }
  }

  private void resetDefaultsButtonActionPerformed(final ActionEvent e) {
    @NonNls final ResourceBundle bundle = ResourceBundle.getBundle(MaterialThemeBundle.BUNDLE);

    final int answer = Messages.showYesNoDialog(bundle.getString("MTForm.dialog.resetDefaults.consent"),
      bundle.getString("MTForm.resetDefaultsButton.text"),
      Messages.getWarningIcon());
    if (answer == Messages.YES) {
      final MTConfig config = MTConfig.getInstance();
      config.resetSettings();
      setFormState(config);
    }
  }

  private void useMaterialFontCheckboxActionPerformed(final ActionEvent e) {
    if (useMaterialFontCheckbox.isSelected()) {
      showFontWarningDialog();
    }
  }

  private void overrideAccentCheckboxActionPerformed(final ActionEvent e) {
    enableDisableAccentColor(isOverrideAccents());
  }

  private void tabFontSizeCheckboxActionPerformed(final ActionEvent e) {
    enableDisableTabFontSize(tabFontSizeCheckbox.isSelected());
  }

  //endregion

  private void darkTitleBarCheckboxActionPerformed(final ActionEvent e) {
    // TODO add your code here
    if (darkTitleBarCheckbox.isSelected() && SystemInfo.isMac && SystemInfo.isJavaVersionAtLeast(11)) {
      showTitleBarDialog();
    }
  }

  private void accentModeCheckboxActionPerformed(final ActionEvent e) {
    enableDisableSecondAccentColor(isAccentMode());
  }

  private void useMaterialWallpapersCheckboxActionPerformed(final ActionEvent e) {
    if (useMaterialWallpapersCheckbox.isSelected()) {

      final int dialog = Messages.showOkCancelDialog(
        MaterialThemeBundle.message("MTForm.materialWallPapers.warning.message"),
        MaterialThemeBundle.message("MTForm.materialWallPapers.warning.title"),
        CommonBundle.getOkButtonText(),
        CommonBundle.getCancelButtonText(),
        Messages.getWarningIcon());

      if (dialog == Messages.CANCEL) {
        useMaterialWallpapersCheckbox.setSelected(false);
      }
    }

  }

  @SuppressWarnings({"MethodWithMoreThanThreeNegations",
    "OverlyLongMethod",
    "OverlyLongLambda",
    "HardCodedStringLiteral",
    "HardcodedFileSeparator"})
  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    final ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    final DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
    content = new JPanel();
    settingsSep = compFactory.createSeparator(bundle.getString("MTForm.settingsSep.text"));
    mainSettingsPanel = new JPanel();
    selectedThemeLabel = new JLabel();
    themeComboBox = new ComboBox<>();
    isContrastModeCheckbox = new JCheckBox();
    highContrastCheckbox = new JCheckBox();
    customAccentColorLabel = new JLabel();
    customAccentColorChooser = new ColorPanel();
    overrideAccentCheckbox = new JCheckBox();
    fileColorsLink = new LinkLabel();
    advSettingsSep = compFactory.createSeparator(bundle.getString("MTForm.advancedSettingsSep.text"));
    tabbedPane1 = new JTabbedPane();
    tabPanel = new JPanel();
    tabsDesc = compFactory.createLabel(bundle.getString("MTForm.tabsDesc.textWithMnemonic"));
    activeTabHighlightCheckbox = new JCheckBox();
    activeTabHighlightColor = new ColorPanel();
    final JLabel thicknessLabel = new JLabel();
    highlightSpinner = new JSpinner();
    isUpperCaseTabsCheckbox = new JCheckBox();
    final JLabel tabHeight = new JLabel();
    tabHeightSpinner = new JSpinner();
    positionLabel = new JLabel();
    tabHighlightPositionComboBox = new ComboBox<>();
    tabFontSizeCheckbox = new JCheckBox();
    tabFontSizeSpinner = new JSpinner();
    final JPanel compactPanel = new JPanel();
    panelDesc = compFactory.createLabel(bundle.getString("MTForm.panelDesc.textWithMnemonic"));
    isCompactStatusbarCheckbox = new JCheckBox();
    isCompactTablesCheckbox = new JCheckBox();
    compactDropdownsCheckbox = new JCheckBox();
    isCompactMenusCheckbox = new JCheckBox();
    projectViewPanel = new JPanel();
    projectViewDesc = compFactory.createLabel(bundle.getString("MTForm.projectViewDesc.textWithMnemonic"));
    isCompactSidebarCheckbox = new JCheckBox();
    customSidebarSpinner = new JSpinner();
    customTreeIndentCheckbox = new JCheckBox();
    leftLabel = new JLabel();
    leftIndentSpinner = new JSpinner();
    rightLabel = new JLabel();
    rightSpinner = new JSpinner();
    selectedIndicatorLabel = new JLabel();
    indicatorStyleComboBox = new ComboBox<>();
    final JLabel indicatorThicknessLabel = new JLabel();
    indicatorThicknessSpinner = new JSpinner();
    styledDirectoriesCheckbox = new JCheckBox();
    directoriesColorLink = new LinkLabel();
    fontSizeCheckbox = new JCheckBox();
    fontSizeSpinner = new JSpinner();
    componentsPanel = new JPanel();
    componentDesc = compFactory.createLabel(bundle.getString("MTForm.componentDesc.textWithMnemonic"));
    upperCaseButtonsCheckbox = new JCheckBox();
    accentScrollbarsCheckbox = new JCheckBox();
    scrollbarsLink = new LinkLabel();
    themedScrollbarsCheckbox = new JCheckBox();
    accentModeCheckbox = new JCheckBox();
    secondAccentLabel = new JLabel();
    secondAccentColorChooser = new ColorPanel();
    tabShadowCheckbox = new JCheckBox();
    featuresPanel = new JPanel();
    featuresDesc = compFactory.createLabel(bundle.getString("MTForm.featuresDesc.textWithMnemonic"));
    useMaterialFontCheckbox = new JCheckBox();
    fileColorsCheckbox = new JCheckBox();
    fileStatusColorsLink = new LinkLabel();
    useMaterialWallpapersCheckbox = new JCheckBox();
    otherTweaksPanel = new JPanel();
    tweaksDesc = compFactory.createLabel(bundle.getString("MTForm.tweaksDesc.textWithMnemonic"));
    isThemeInStatusCheckbox = new JCheckBox();
    darkTitleBarCheckbox = new JCheckBox();
    codeAdditionsCheckBox = new JCheckBox();
    isColoredOpenedDirsCheckbox = new JCheckBox();
    resetDefaultsButton = new JButton();

    //======== content ========
    {
      content.setAutoscrolls(true);
      content.setRequestFocusEnabled(false);
      content.setVerifyInputWhenFocusTarget(false);
      content.setBorder(null);
      content.setPreferredSize(null);
      content.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      content.setMinimumSize(null);
      content.setLayout(new MigLayout(
        "insets 0,hidemode 1,aligny top,gap 0 0",
        // columns
        "[fill]",
        // rows
        "[]" +
          "[fill]" +
          "[fill]" +
          "[259,fill]" +
          "[]"));
      content.add(settingsSep, "cell 0 0,gapx 16,gapy 10 10");

      //======== mainSettingsPanel ========
      {
        mainSettingsPanel.setBorder(null);
        mainSettingsPanel.setLayout(new MigLayout(
          "fillx,align left center",
          // columns
          "[grow 1,shrink 0,fill]",
          // rows
          "[]" +
            "[]" +
            "[]" +
            "[grow]0" +
            "[]" +
            "[]"));

        //---- selectedThemeLabel ----
        selectedThemeLabel.setText(bundle.getString("MTForm.selectedThemeLabel.text"));
        selectedThemeLabel.setIcon(new ImageIcon(getClass().getResource("/icons/mt/themeSwitch.png")));
        selectedThemeLabel.setFont(UIManager.getFont("ComboBox.font"));
        selectedThemeLabel.setIconTextGap(8);
        selectedThemeLabel.setToolTipText(bundle.getString("MTForm.selectedThemeLabel.toolTipText"));
        mainSettingsPanel.add(selectedThemeLabel, "pad 0 2 0 0,cell 0 0,growx");

        //---- themeComboBox ----
        themeComboBox.setToolTipText(bundle.getString("MTForm.themeComboBox.toolTipText"));
        mainSettingsPanel.add(themeComboBox, "cell 1 0");

        //---- isContrastModeCheckbox ----
        isContrastModeCheckbox.setText(bundle.getString("MTForm.contrastCheckBox.text"));
        isContrastModeCheckbox.setToolTipText(bundle.getString("MTForm.contrastCheckBox.toolTipText"));
        mainSettingsPanel.add(isContrastModeCheckbox, "cell 0 1");

        //---- highContrastCheckbox ----
        highContrastCheckbox.setText(bundle.getString("MTForm.highContrastCheckbox.text"));
        highContrastCheckbox.setToolTipText(bundle.getString("MTForm.highContrastCheckbox.toolTipText"));
        mainSettingsPanel.add(highContrastCheckbox, "cell 0 2");

        //---- customAccentColorLabel ----
        customAccentColorLabel.setText(bundle.getString("MTForm.customAccentColorLabel.text"));
        customAccentColorLabel.setToolTipText(bundle.getString("MTForm.customAccentColorLabel.toolTipText"));
        customAccentColorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        customAccentColorLabel.setIcon(new ImageIcon(getClass().getResource("/icons/mt/customAccent.png")));
        customAccentColorLabel.setIconTextGap(8);
        mainSettingsPanel.add(customAccentColorLabel, "pad 0 2 0 0,cell 0 3,growx");

        //---- customAccentColorChooser ----
        customAccentColorChooser.setMinimumSize(new Dimension(10, 18));
        customAccentColorChooser.setPreferredSize(new Dimension(61, 26));
        mainSettingsPanel.add(customAccentColorChooser, "cell 1 3,alignx right,growx 0");

        //---- overrideAccentCheckbox ----
        overrideAccentCheckbox.setText(bundle.getString("MTForm.overrideAccentCheckbox.text"));
        overrideAccentCheckbox.setFont(overrideAccentCheckbox.getFont().deriveFont(overrideAccentCheckbox.getFont().getSize() - 1f));
        overrideAccentCheckbox.setToolTipText(bundle.getString("MTForm.overrideAccentCheckbox.toolTipText"));
        overrideAccentCheckbox.addActionListener(e -> overrideAccentCheckboxActionPerformed(e));
        mainSettingsPanel.add(overrideAccentCheckbox, "cell 0 4,gapx 20");

        //---- fileColorsLink ----
        fileColorsLink.setText(bundle.getString("MTForm.fileColorsLink.text"));
        fileColorsLink.setForeground(UIManager.getColor("Link.activeForeground"));
        fileColorsLink.setToolTipText(bundle.getString("MTForm.fileColorsLink.toolTipText"));
        fileColorsLink.setLabelFor(null);
        mainSettingsPanel.add(fileColorsLink, "cell 0 5 2 1");
      }
      content.add(mainSettingsPanel, "cell 0 1");
      content.add(advSettingsSep, "cell 0 2,aligny center,growy 0,gapx 16,gapy 10 10");

      //======== tabbedPane1 ========
      {
        tabbedPane1.setMinimumSize(null);
        tabbedPane1.setPreferredSize(null);

        //======== tabPanel ========
        {
          tabPanel.setLayout(new MigLayout(
            "fillx,hidemode 3,align left top",
            // columns
            "[fill]" +
              "[grow1, fill]",
            // rows
            "[]" +
              "[]" +
              "[]" +
              "[]" +
              "[]" +
              "[]" +
              "[]"));

          //---- tabsDesc ----
          tabsDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          tabPanel.add(tabsDesc, "cell 0 0 2 1");

          //---- activeTabHighlightCheckbox ----
          activeTabHighlightCheckbox.setText(bundle.getString("MTForm.activeTabHighlightCheckbox.text"));
          activeTabHighlightCheckbox.setToolTipText(bundle.getString("MTForm.activeTabHighlightCheckbox.toolTipText"));
          activeTabHighlightCheckbox.addActionListener(e -> activeTabHighlightCheckboxActionPerformed(e));
          tabPanel.add(activeTabHighlightCheckbox, "cell 0 1,align left center,grow 0 0");
          tabPanel.add(activeTabHighlightColor, "cell 1 1,align right center,grow 0 0");

          //---- thicknessLabel ----
          thicknessLabel.setHorizontalTextPosition(SwingConstants.LEADING);
          thicknessLabel.setLabelFor(highlightSpinner);
          thicknessLabel.setText(bundle.getString("MTForm.thicknessLabel.text"));
          thicknessLabel.setToolTipText(bundle.getString("MTForm.thicknessLabel.toolTipText"));
          tabPanel.add(thicknessLabel, "pad 0,cell 0 2,aligny center,grow 100 0");

          //---- highlightSpinner ----
          highlightSpinner.setToolTipText(bundle.getString("MTForm.highlightSpinner.toolTipText"));
          tabPanel.add(highlightSpinner, "cell 1 2,align right center,grow 0 0,width 80:80:80");

          //---- isUpperCaseTabsCheckbox ----
          isUpperCaseTabsCheckbox.setText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.text"));
          isUpperCaseTabsCheckbox.setToolTipText(bundle.getString("MTForm.isUpperCaseTabsCheckbox.toolTipText"));
          tabPanel.add(isUpperCaseTabsCheckbox, "cell 0 3,align left center,grow 0 0");

          //---- tabHeight ----
          tabHeight.setHorizontalTextPosition(SwingConstants.LEADING);
          tabHeight.setLabelFor(highlightSpinner);
          tabHeight.setText(bundle.getString("MTForm.tabHeight.text"));
          tabHeight.setToolTipText(bundle.getString("MTForm.tabHeight.toolTipText"));
          tabPanel.add(tabHeight, "pad 0,cell 0 4,aligny center,grow 100 0");

          //---- tabHeightSpinner ----
          tabHeightSpinner.setToolTipText(bundle.getString("MTForm.tabHeightSpinner.toolTipText"));
          tabPanel.add(tabHeightSpinner, "cell 1 4,align right center,grow 0 0,width 80:80:80");

          //---- positionLabel ----
          positionLabel.setText(bundle.getString("MTForm.positionLabel.text"));
          positionLabel.setToolTipText(bundle.getString("MTForm.positionLabel.toolTipText"));
          tabPanel.add(positionLabel, "cell 0 5,aligny center,growy 0");

          //---- tabHighlightPositionComboBox ----
          tabHighlightPositionComboBox.setToolTipText(bundle.getString("MTForm.tabHighlightPositionComboBox.toolTipText"));
          tabPanel.add(tabHighlightPositionComboBox, "cell 1 5,align right center,grow 0 0,width 120:120:120");

          //---- tabFontSizeCheckbox ----
          tabFontSizeCheckbox.setText(bundle.getString("MTForm.tabFontSizeCheckbox.text"));
          tabFontSizeCheckbox.setToolTipText(bundle.getString("MTForm.tabFontSizeCheckbox.toolTipText"));
          tabFontSizeCheckbox.addActionListener(e -> {
            fontSizeCheckboxActionPerformed(e);
            tabFontSizeCheckboxActionPerformed(e);
          });
          tabPanel.add(tabFontSizeCheckbox, "cell 0 6");

          //---- tabFontSizeSpinner ----
          tabFontSizeSpinner.setToolTipText(bundle.getString("MTForm.tabFontSizeSpinner.toolTipText"));
          tabPanel.add(tabFontSizeSpinner, "cell 1 6,align right center,grow 0 0,width 80:80:80");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.tabPanel.tab.title"), null, tabPanel, bundle.getString("MTForm.tabPanel.tab" +
          ".toolTipText"));

        //======== compactPanel ========
        {
          compactPanel.setBorder(null);
          compactPanel.setMinimumSize(null);
          compactPanel.setPreferredSize(null);
          compactPanel.setLayout(new MigLayout(
            "fillx,hidemode 3,gap 10 5",
            // columns
            "[grow, fill]",
            // rows
            "[fill]" +
              "[]" +
              "[]" +
              "[]" +
              "[]"));

          //---- panelDesc ----
          panelDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          compactPanel.add(panelDesc, "cell 0 0");

          //---- isCompactStatusbarCheckbox ----
          isCompactStatusbarCheckbox.setText(bundle.getString("MTForm.isCompactStatusbarCheckbox.text"));
          isCompactStatusbarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactStatusbarCheckbox.toolTipText"));
          compactPanel.add(isCompactStatusbarCheckbox, "cell 0 1,align left center,grow 0 0");

          //---- isCompactTablesCheckbox ----
          isCompactTablesCheckbox.setText(bundle.getString("MTForm.isCompactTablesCheckbox.text"));
          isCompactTablesCheckbox.setToolTipText(bundle.getString("MTForm.isCompactTablesCheckbox.toolTipText"));
          compactPanel.add(isCompactTablesCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- compactDropdownsCheckbox ----
          compactDropdownsCheckbox.setText(bundle.getString("MTForm.compactDropdownsCheckbox.text"));
          compactDropdownsCheckbox.setToolTipText(bundle.getString("MTForm.compactDropdownsCheckbox.toolTipText"));
          compactPanel.add(compactDropdownsCheckbox, "cell 0 3");

          //---- isCompactMenusCheckbox ----
          isCompactMenusCheckbox.setText(bundle.getString("MTForm.isCompactMenusCheckbox.text"));
          isCompactMenusCheckbox.setToolTipText(bundle.getString("MTForm.isCompactMenusCheckbox.toolTipText"));
          compactPanel.add(isCompactMenusCheckbox, "cell 0 4");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.compactPanel.tab.title"), null, compactPanel, bundle.getString("MTForm.compactPanel" +
          ".tab.toolTipText"));

        //======== projectViewPanel ========
        {
          projectViewPanel.setBorder(null);
          projectViewPanel.setLayout(new MigLayout(
            "fillx,hidemode 3,align left top",
            // columns
            "[322,fill]" +
              "[fill]",
            // rows
            "[]" +
              "[]" +
              "[]" +
              "[]" +
              "[]" +
              "[]" +
              "[]" +
              "[]"));

          //---- projectViewDesc ----
          projectViewDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          projectViewPanel.add(projectViewDesc, "cell 0 0 2 1");

          //---- isCompactSidebarCheckbox ----
          isCompactSidebarCheckbox.setText(bundle.getString("MTForm.isCompactSidebarCheckbox.text"));
          isCompactSidebarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactSidebarCheckbox.toolTipText"));
          isCompactSidebarCheckbox.addActionListener(e -> isCompactSidebarCheckboxActionPerformed(e));
          projectViewPanel.add(isCompactSidebarCheckbox, "cell 0 1,align left center,grow 0 0");

          //---- customSidebarSpinner ----
          customSidebarSpinner.setToolTipText(bundle.getString("MTForm.customSidebarSpinner.toolTipText"));
          projectViewPanel.add(customSidebarSpinner, "cell 1 1,align right center,grow 0 0,width 80:80:80");

          //---- customTreeIndentCheckbox ----
          customTreeIndentCheckbox.setText(bundle.getString("MTForm.customTreeIndentCheckbox.text"));
          customTreeIndentCheckbox.setToolTipText(bundle.getString("MTForm.customTreeIndentCheckbox.toolTipText"));
          customTreeIndentCheckbox.addActionListener(e -> customTreeIndentCheckboxActionPerformed(e));
          projectViewPanel.add(customTreeIndentCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- leftLabel ----
          leftLabel.setText(bundle.getString("MTForm.leftLabel.text"));
          leftLabel.setFont(leftLabel.getFont().deriveFont(leftLabel.getFont().getSize() - 2f));
          projectViewPanel.add(leftLabel, "cell 1 2");

          //---- leftIndentSpinner ----
          leftIndentSpinner.setToolTipText(bundle.getString("MTForm.leftIndentSpinner.toolTipText"));
          leftIndentSpinner.setPreferredSize(new Dimension(80, 30));
          leftIndentSpinner.setMinimumSize(new Dimension(80, 30));
          projectViewPanel.add(leftIndentSpinner, "cell 1 2");

          //---- rightLabel ----
          rightLabel.setText(bundle.getString("MTForm.rightLabel.text"));
          rightLabel.setFont(rightLabel.getFont().deriveFont(rightLabel.getFont().getSize() - 2f));
          projectViewPanel.add(rightLabel, "cell 1 2");

          //---- rightSpinner ----
          rightSpinner.setToolTipText(bundle.getString("MTForm.rightSpinner.toolTipText"));
          projectViewPanel.add(rightSpinner, "cell 1 2,align right center,grow 0 0,width 80:80:80");

          //---- selectedIndicatorLabel ----
          selectedIndicatorLabel.setText(bundle.getString("MTForm.selectedIndicatorLabel.text"));
          selectedIndicatorLabel.setToolTipText(bundle.getString("MTForm.selectedIndicatorLabel.toolTipText"));
          projectViewPanel.add(selectedIndicatorLabel, "cell 0 3");

          //---- indicatorStyleComboBox ----
          indicatorStyleComboBox.setToolTipText(bundle.getString("MTForm.indicatorStyleComboBox.toolTipText"));
          projectViewPanel.add(indicatorStyleComboBox, "cell 1 3,align right center,grow 0 0,width 120:120:120");

          //---- indicatorThicknessLabel ----
          indicatorThicknessLabel.setHorizontalTextPosition(SwingConstants.LEADING);
          indicatorThicknessLabel.setLabelFor(highlightSpinner);
          indicatorThicknessLabel.setText(bundle.getString("MTForm.indicatorThicknessLabel.text"));
          indicatorThicknessLabel.setToolTipText(bundle.getString("MTForm.indicatorThicknessLabel.toolTipText"));
          projectViewPanel.add(indicatorThicknessLabel, "pad 0 16 0 0,cell 0 4");

          //---- indicatorThicknessSpinner ----
          indicatorThicknessSpinner.setToolTipText(bundle.getString("MTForm.indicatorThicknessSpinner.toolTipText"));
          projectViewPanel.add(indicatorThicknessSpinner, "cell 1 4,alignx right,growx 0");

          //---- styledDirectoriesCheckbox ----
          styledDirectoriesCheckbox.setText(bundle.getString("MTForm.styledDirectoriesCheckbox.text"));
          styledDirectoriesCheckbox.setToolTipText(bundle.getString("MTForm.styledDirectoriesCheckbox.tooltipText"));
          projectViewPanel.add(styledDirectoriesCheckbox, "cell 0 5");

          //---- directoriesColorLink ----
          directoriesColorLink.setText(bundle.getString("MTForm.directoriesColorLink.text"));
          directoriesColorLink.setForeground(UIManager.getColor("Link.activeForeground"));
          directoriesColorLink.setHorizontalAlignment(SwingConstants.RIGHT);
          directoriesColorLink.setToolTipText(bundle.getString("MTForm.directoriesColorLink.toolTipText"));
          projectViewPanel.add(directoriesColorLink, "cell 1 5");

          //---- fontSizeCheckbox ----
          fontSizeCheckbox.setText(bundle.getString("MTForm.fontSizeCheckbox.text"));
          fontSizeCheckbox.setToolTipText(bundle.getString("MTForm.fontSizeCheckbox.toolTipText"));
          fontSizeCheckbox.addActionListener(e -> fontSizeCheckboxActionPerformed(e));
          projectViewPanel.add(fontSizeCheckbox, "cell 0 6");

          //---- fontSizeSpinner ----
          fontSizeSpinner.setToolTipText(bundle.getString("MTForm.fontSizeSpinner.toolTipText"));
          projectViewPanel.add(fontSizeSpinner, "cell 1 6,align right center,grow 0 0,width 80:80:80");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.projectViewPanel.tab.title"), null, projectViewPanel, bundle.getString("MTForm" +
          ".projectViewPanel.tab.toolTipText"));

        //======== componentsPanel ========
        {
          componentsPanel.setBorder(null);
          componentsPanel.setLayout(new MigLayout(
            "fillx,hidemode 3,align left top",
            // columns
            "[fill]",
            // rows
            "[]" +
              "[]" +
              "[]" +
              "[]" +
              "[]"));

          //---- componentDesc ----
          componentDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          componentsPanel.add(componentDesc, "cell 0 0");

          //---- upperCaseButtonsCheckbox ----
          upperCaseButtonsCheckbox.setText(bundle.getString("MTForm.upperCaseButtonsCheckbox.text"));
          upperCaseButtonsCheckbox.setToolTipText(bundle.getString("MTForm.upperCaseButtonsCheckbox.toolTipText"));
          componentsPanel.add(upperCaseButtonsCheckbox, "cell 0 1");

          //---- accentScrollbarsCheckbox ----
          accentScrollbarsCheckbox.setText(bundle.getString("MTForm.accentScrollbarsCheckbox.text"));
          accentScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.accentScrollbarsCheckbox.toolTipText"));
          componentsPanel.add(accentScrollbarsCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- scrollbarsLink ----
          scrollbarsLink.setText(bundle.getString("MTForm.scrollbarsLink.text"));
          scrollbarsLink.setForeground(UIManager.getColor("Link.activeForeground"));
          scrollbarsLink.setHorizontalAlignment(SwingConstants.RIGHT);
          scrollbarsLink.setToolTipText(bundle.getString("MTForm.scrollbarsLink.toolTipText"));
          componentsPanel.add(scrollbarsLink, "cell 0 2");

          //---- themedScrollbarsCheckbox ----
          themedScrollbarsCheckbox.setText(bundle.getString("MTForm.themedScrollbarsCheckbox.text"));
          themedScrollbarsCheckbox.setToolTipText(bundle.getString("MTForm.themedScrollbarsCheckbox.toolTipText"));
          componentsPanel.add(themedScrollbarsCheckbox, "cell 0 3");

          //---- accentModeCheckbox ----
          accentModeCheckbox.setText(bundle.getString("MTForm.accentModeCheckbox.text"));
          accentModeCheckbox.setToolTipText(bundle.getString("MTForm.accentModeCheckbox.toolTipText"));
          accentModeCheckbox.addActionListener(e -> accentModeCheckboxActionPerformed(e));
          componentsPanel.add(accentModeCheckbox, "cell 0 5");

          //---- secondAccentLabel ----
          secondAccentLabel.setText(bundle.getString("MTForm.secondAccentLabel.text"));
          secondAccentLabel.setToolTipText(bundle.getString("MTForm.secondAccentLabel.toolTipText"));
          componentsPanel.add(secondAccentLabel, "cell 0 5");

          //---- secondAccentColorChooser ----
          secondAccentColorChooser.setMinimumSize(new Dimension(10, 18));
          secondAccentColorChooser.setPreferredSize(new Dimension(61, 26));
          componentsPanel.add(secondAccentColorChooser, "cell 0 5,alignx right,growx 0");

          //---- tabShadowCheckbox ----
          tabShadowCheckbox.setText(bundle.getString("MTForm.tabShadowCheckbox.text"));
          tabShadowCheckbox.setToolTipText(bundle.getString("MTForm.tabShadowCheckbox.toolTipText"));
          componentsPanel.add(tabShadowCheckbox, "cell 0 4");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.componentsPanel.tab.title"), null, componentsPanel, bundle.getString("MTForm" +
          ".componentsPanel.tab.toolTipText"));

        //======== featuresPanel ========
        {
          featuresPanel.setBorder(null);
          featuresPanel.setLayout(new MigLayout(
            "fillx,hidemode 3,align left top",
            // columns
            "[fill]" +
              "[fill]",
            // rows
            "[]" +
              "[]" +
              "[]" +
              "[]"));

          //---- featuresDesc ----
          featuresDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          featuresPanel.add(featuresDesc, "cell 0 0");

          //---- useMaterialFontCheckbox ----
          useMaterialFontCheckbox.setText(bundle.getString("MTForm.useMaterialFontCheckbox.text"));
          useMaterialFontCheckbox.setToolTipText(bundle.getString("MTForm.useMaterialFontCheckbox.tooltipText"));
          useMaterialFontCheckbox.addActionListener(e -> useMaterialFontCheckboxActionPerformed(e));
          featuresPanel.add(useMaterialFontCheckbox, "cell 0 1,align left center,grow 0 0");

          //---- fileColorsCheckbox ----
          fileColorsCheckbox.setText(bundle.getString("MTForm.fileColorsCheckbox.text"));
          fileColorsCheckbox.setToolTipText(bundle.getString("MTForm.fileColorsCheckbox.toolTipText"));
          featuresPanel.add(fileColorsCheckbox, "cell 0 2");

          //---- fileStatusColorsLink ----
          fileStatusColorsLink.setText(bundle.getString("MTForm.fileStatusColorsLink.text"));
          fileStatusColorsLink.setForeground(UIManager.getColor("Link.activeForeground"));
          fileStatusColorsLink.setToolTipText(bundle.getString("MTForm.fileStatusColorsLink.toolTipText"));
          featuresPanel.add(fileStatusColorsLink, "cell 1 2");

          //---- useMaterialWallpapersCheckbox ----
          useMaterialWallpapersCheckbox.setText(bundle.getString("MTForm.useMaterialWallpapersCheckbox.text"));
          useMaterialWallpapersCheckbox.setToolTipText(bundle.getString("MTForm.useMaterialWallpapersCheckbox.toolTipText"));
          useMaterialWallpapersCheckbox.addActionListener(e -> useMaterialWallpapersCheckboxActionPerformed(e));
          featuresPanel.add(useMaterialWallpapersCheckbox, "cell 0 3,align left center,grow 0 0");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.featuresPanel.tab.title"), null, featuresPanel, bundle.getString("MTForm" +
          ".featuresPanel.tab.toolTipText"));

        //======== otherTweaksPanel ========
        {
          otherTweaksPanel.setBorder(null);
          otherTweaksPanel.setLayout(new MigLayout(
            "fillx,hidemode 3,align left top",
            // columns
            "[fill]",
            // rows
            "[]" +
              "[]" +
              "[]" +
              "[]" +
              "[]"));

          //---- tweaksDesc ----
          tweaksDesc.setForeground(UIManager.getColor("Label.disabledForeground"));
          otherTweaksPanel.add(tweaksDesc, "cell 0 0");

          //---- isThemeInStatusCheckbox ----
          isThemeInStatusCheckbox.setText(bundle.getString("MTForm.themeStatusBar.text"));
          isThemeInStatusCheckbox.setToolTipText(bundle.getString("MTForm.themeStatusBar.toolTipText"));
          otherTweaksPanel.add(isThemeInStatusCheckbox, "cell 0 1,align left center,grow 0 0");

          //---- darkTitleBarCheckbox ----
          darkTitleBarCheckbox.setText(bundle.getString("MTForm.themedTitleBarCheckbox.text"));
          darkTitleBarCheckbox.setToolTipText(bundle.getString("MTForm.themedTitleBarCheckbox.toolTipText"));
          otherTweaksPanel.add(darkTitleBarCheckbox, "cell 0 2,align left center,grow 0 0");

          //---- codeAdditionsCheckBox ----
          codeAdditionsCheckBox.setText(bundle.getString("MTForm.codeAdditionsCheckBox.text"));
          codeAdditionsCheckBox.setToolTipText(bundle.getString("MTForm.codeAdditionsCheckBox.toolTipText"));
          otherTweaksPanel.add(codeAdditionsCheckBox, "cell 0 3,align left center,grow 0 0");

          //---- isColoredOpenedDirsCheckbox ----
          isColoredOpenedDirsCheckbox.setText(bundle.getString("MTForm.isColoredOpenedDirsCheckbox.text"));
          isColoredOpenedDirsCheckbox.setToolTipText(bundle.getString("MTForm.isColoredOpenedDirsCheckbox.toolTipText"));
          otherTweaksPanel.add(isColoredOpenedDirsCheckbox, "cell 0 4,align left center,grow 0 0");
        }
        tabbedPane1.addTab(bundle.getString("MTForm.otherTweaksPanel.tab.title"), null, otherTweaksPanel, bundle.getString("MTForm" +
          ".otherTweaksPanel.tab.toolTipText"));
      }
      content.add(tabbedPane1, "pad 0,cell 0 3,growx,gapx 10 10,gapy 10 10");

      //---- resetDefaultsButton ----
      resetDefaultsButton.setText(bundle.getString("MTForm.resetDefaultsButton.text"));
      resetDefaultsButton.setToolTipText(bundle.getString("MTForm.resetDefaultsButton.toolTipText"));
      resetDefaultsButton.addActionListener(e -> resetDefaultsButtonActionPerformed(e));
      content.add(resetDefaultsButton, "pad 0,cell 0 4,align trailing center,grow 0 0,wmin 200");
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents

    fileColorsLink.setIcon(IconLoader.findIcon("/icons/mt/link2.svg"));
    directoriesColorLink.setIcon(IconLoader.findIcon("/icons/mt/link2.svg"));
    fileStatusColorsLink.setIcon(IconLoader.findIcon("/icons/mt/link2.svg"));
    scrollbarsLink.setIcon(IconLoader.findIcon("/icons/mt/link2.svg"));

    darkTitleBarCheckbox.addActionListener(this::isDarkTitleBarActionPerformed);

    darkTitleBarCheckbox.setEnabled((SystemInfo.isWin10OrNewer) || (SystemInfo.isMac));

    // Themes
    themeComboBox.setModel(new DefaultComboBoxModel<>(MTThemes.getAllThemes()));
    themeComboBox.setRenderer(new ListCellRendererWrapper<MTThemeFacade>() {
      @Override
      public void customize(final JList list, final MTThemeFacade value, final int index, final boolean selected, final boolean hasFocus) {
        final Icon baseIcon;
        if (value == null) {
          return;
        }
        baseIcon = value.getIcon();
        setIcon(baseIcon);
        setText(value.getThemeName());
      }
    });

    // Indicator
    indicatorStyleComboBox.setModel(new DefaultComboBoxModel<>(IndicatorStyles.values()));

    // Positions
    tabHighlightPositionComboBox.setModel(new DefaultComboBoxModel<>(TabHighlightPositions.values()));

    fileColorsLink.setListener((aSource, aLinkData) -> {
      final Settings settings = Settings.KEY.getData(DataManager.getInstance().getDataContext(content));

      if (settings != null) {
        settings.select(settings.find(MTCustomThemeConfigurable.class));
      }
    }, null);

    fileStatusColorsLink.setListener((aSource, aLinkData) -> {
      final Settings settings = Settings.KEY.getData(DataManager.getInstance().getDataContext(content));

      if (settings != null) {
        final SearchableConfigurable subConfigurable =
          Objects.requireNonNull(settings.find(ColorAndFontOptions.class)).findSubConfigurable(MTFileColorsPage.class);
        if (subConfigurable != null) {
          settings.select(subConfigurable);
        }
      }
    }, null);

    directoriesColorLink.setListener((aSource, aLinkData) -> {
      final Settings settings = Settings.KEY.getData(DataManager.getInstance().getDataContext(content));

      if (settings != null) {
        final SearchableConfigurable subConfigurable =
          Objects.requireNonNull(settings.find(ColorAndFontOptions.class)).findSubConfigurable(MTFileColorsPage.class);
        if (subConfigurable != null) {
          settings.select(subConfigurable, "Directories");
        }
      }
    }, null);

  }
}
