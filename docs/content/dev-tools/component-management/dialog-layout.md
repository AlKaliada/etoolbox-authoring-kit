<!--
layout: content
title: Dialog Layout
seoTitle: Dialog Layout - Exadel Authoring Kit
order: 2
-->

## Laying out your dialogs

### Tabbed layout

* [@Tabs](https://javadoc.io/doc/com.exadel.etoolbox/etoolbox-authoring-kit-core/latest/com/exadel/aem/toolkit/api/annotations/layouts/Tabs.html)
* Resource type: /libs/granite/ui/components/coral/foundation/tabs
* See spec: [Tabs](https://developer.adobe.com/experience-manager/reference-materials/6-5/granite-ui/api/jcr_root/libs/granite/ui/components/coral/foundation/tabs/index.html)

If you need your dialog split in meaningful sections, tabs are probably the most common way to do the task. There can be reasons to create a tabbed dialog even if a single section would be sufficient. That's because the tab would have a title that would additionally hint at the dialog settings' role (something like "General config").

There are several ways to create tabbed dialogs. First, you may need to mark a nested class of your `@Dialog`-annotated class with `@Tab` annotation. The _title_ property of `@Tab` will be used as the tab's node name and non-alphanumeric characters will be skipped (for example, `@Tab(title="First tab title!")` will produce _\<firstTabTitle>_ tag)

```java
@Dialog
public class Dialog {
    @Tab(title = "First tab")
    static class Tab1 {
        @DialogField(label="Field in the first tab")
        @TextField
        String field1;
    }
    @Tab(title = "Second tab")
    static class Tab2 {
        @DialogField(label="Field in the second tab")
        @TextField
        String field2;
    }
}
```

The other way of laying out tabs is to define an array of `@Tab` within `@Tabs` annotation attached to the dialog file. Then, to settle a field to a certain tab you will need  to add `@Place` annotation to this particular field. The values of `@Place` must correspond to the value of the desired tab. This is a somewhat more flexible technique which avoids creating nested classes and allows freely moving fields (same as for `@AccordionPanel`). But please ensure that the tab title is specified everywhere in the same format.

```java
@AemComponent(
    path= "path/to/my/component",
    title = "My Component Dialog"
)
@Dialog
@Tabs({
    @Tab(title = "First tab"),
    @Tab(title = "Second tab"),
    @Tab(title = "Third tab")
})
public class MyComponent {
    @DialogField(label = "Field in the first tab")
    @TextField
    @PlaceOn("First tab")
    String field1;

    @DialogField(label = "Field in the second tab")
    @TextField
    @PlaceOn("Second tab")
    String field2;

    @DialogField(description = "Field in the third tab")
    @TextField
    @PlaceOn("Third tab")
    String field3;
}
```
Tabs declared this way are virtually "inherited" from a superclass to a descendant class. The following setup is valid:

```java
@Tabs({
    @Tab(title = "First tab"),
    @Tab(title = "Second tab")
})
public class MyComponentAncestor {/* ... */}

@AemComponent(
    path= "path/to/my/component",
    title = "My Component Dialog"
)
@Dialog
@Tabs(@Tab(title = "Third tab"))
public class MyComponent extends MyComponentAncestor {
    @DialogField(label = "Field in the first tab")
    @TextField
    @PlaceOn("First tab")
    String field1;
}
```

Note that the ancestor class does not have to be declared `@AemComponent` itself (however may as well stand as a separate entity). If not every tab present in an ancestor needs to be rendered in a child, the child class can have `@Ignore(sections = "Tab from ancestor")` attached. See more on this in [Reusing code and making it brief](./reusing-code.md), the "Sections inheritance" division.

Additionally, you can facilitate a tabbed area *within* the dialog or one of its sections to keep your visual elements well organized. See the "Tabs" division in [Component Structure](./component-structure.md) for particulars.

### Accordion layout

* [@Accordion](https://javadoc.io/doc/com.exadel.etoolbox/etoolbox-authoring-kit-core/latest/com/exadel/aem/toolkit/api/annotations/layouts/Accordion.html)
* Resource type: /libs/granite/ui/components/coral/foundation/accordion
* See spec: [Accordion](https://developer.adobe.com/experience-manager/reference-materials/6-5/granite-ui/api/jcr_root/libs/granite/ui/components/coral/foundation/accordion/index.html)

Creating an accordion is also a widely used way to arrange multiple components within a dialog or a dialog section. This is done with the use of `@Accordion` annotation.

Same as for the tabs, the `@Accordion` annotation can be exploited in two different ways. If added to a Java class itself, it manifests that the whole dialog will be laid out in accordion style, but if added to a particular field or method, it creates a nested accordion within the dialog.

In both cases, `@Accordion` bears an array of `@AccordionPanel`s defined by their titles To settle a field to a certain accordionPanel, you will need  to add a `@Place` annotation to this particular field. The values of `@Place` must correspond to the value of the desired panel.

The other way to create a panel is to declare a nested class annotated with `@AccordionPanel`. You only need to ensure that the panel title is specified everywhere in the exact same format with no extra spaces, etc.

```java
@AemComponent(
    path= "path/to/my/component",
    title = "My Component Dialog"
)
@Dialog
@Accordion({
    @AccordionPanel(title = "First panel"),
    @AccordionPanel(title = "Second panel")
})
public class MyComponent {
    @DialogField(label = "Field in the first panel")
    @TextField
    @Place("First panel")
    String field1;

    @DialogField(label = "Field in the second panel")
    @TextField
    @Place("Second panel")
    String field2;
}
```
Accordion panels inheritance and ignoring works exactly the same as for the tabs. And so is the ability to create a nested accordion within a dialog or its section. See the "Accordion" division in [Component Structure](./component-structure.md).

### Fixed columns layout

* [@FixedColumns](https://javadoc.io/doc/com.exadel.etoolbox/etoolbox-authoring-kit-core/latest/com/exadel/aem/toolkit/api/annotations/layouts/FixedColumns.html)
* Resource type: /libs/granite/ui/components/foundation/layouts/fixedcolumns
* See spec: [FixedColumns](https://developer.adobe.com/experience-manager/reference-materials/6-5/granite-ui/api/jcr_root/libs/granite/ui/components/foundation/layouts/fixedcolumns/index.html)

Fixed columns are the default layout for a Granite UI dialog unless some specific one, such as Tabs or Accordion, is requested. By default, it creates a single fixed-width column.

Most of the time you wouldn't need to specify it at all. Still, you might want to, and it is when more than one column is required.
```java
@AemComponent(
    path= "path/to/my/component",
    title = "My Component Dialog"
)
@Dialog
@FixedColumns({
    @Column(title = "First"),
    @Column(title = "Second")
})
public class MyComponent {
    @DialogField(label = "Field in the first column")
    @TextField
    @Place("First")
    String field1;

    @DialogField(label = "Field in the second column")
    @TextField
    @Place("Second")
    String field2;
}
```
Multiple columns are placed side-by-side if the width of the dialog allows, or else go one under the other. Please note that the title is not rendered in the UI; it is used for merely referring to a particular column in `@Place(...)`.

Then again, same as for Tabs and Accordion, `@FixedColumns` can be used as an in-dialog widget. This is particularly useful when you need to render dialog content screen-centered, as within a Page properties' tab (see "Page properties" section in [Component Structure](./component-structure.md)). For the in-dialog usage two additional properties of `@FixedColumns` can be specified: *margin* and *maximized* (see the Javadoc for more detail).

### See also

- [Component Structure](./component-structure.md)
- [Reusing code and making it brief](./reusing-code.md)
