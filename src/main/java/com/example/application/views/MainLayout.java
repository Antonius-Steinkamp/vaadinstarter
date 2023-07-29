package com.example.application.views;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;

import org.vaadin.lineawesome.LineAwesomeIcon;

import com.example.application.views.about.AboutView;
import com.example.application.views.addressform.AddressFormView;
import com.example.application.views.cardlist.CardListView;
import com.example.application.views.checkoutform.CheckoutFormView;
import com.example.application.views.collaborativemasterdetail.CollaborativeMasterDetailView;
import com.example.application.views.creditcardform.CreditCardFormView;
import com.example.application.views.empty.EmptyView;
import com.example.application.views.gridwithfilters.GridwithFiltersView;
import com.example.application.views.helloworld.HelloWorldView;
import com.example.application.views.imagelist.ImageListView;
import com.example.application.views.masterdetail.PersonMasterDetailView;
import com.example.application.views.masterdetail.TranslationMasterDetailView;
import com.example.application.views.personform.PersonFormView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

	NativeLabel dateLabel = new NativeLabel();

    /**
	 * 
	 */
	private static final long serialVersionUID = -4533394623204260469L;
	private H2 viewTitle;

    public static final DateTimeFormatter LOCAL_DATE_TIME;
    static {
        LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ISO_LOCAL_DATE)
                .appendLiteral(' ')
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .toFormatter();
    }

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("VaadinStarter");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Hello World", HelloWorldView.class, LineAwesomeIcon.GLOBE_SOLID.create()));
        nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new SideNavItem("Card List", CardListView.class, LineAwesomeIcon.LIST_SOLID.create()));
        nav.addItem(new SideNavItem("Empty", EmptyView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new SideNavItem("P-Master-Detail", PersonMasterDetailView.class, LineAwesomeIcon.COLUMNS_SOLID.create()));
        nav.addItem(new SideNavItem("T-Master-Detail", TranslationMasterDetailView.class, LineAwesomeIcon.COLUMNS_SOLID.create()));
        nav.addItem(new SideNavItem("Collaborative Master-Detail", CollaborativeMasterDetailView.class,
                LineAwesomeIcon.COLUMNS_SOLID.create()));
        nav.addItem(new SideNavItem("Person Form", PersonFormView.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new SideNavItem("Address Form", AddressFormView.class, LineAwesomeIcon.MAP_MARKER_SOLID.create()));
        nav.addItem(
                new SideNavItem("Credit Card Form", CreditCardFormView.class, LineAwesomeIcon.CREDIT_CARD.create()));
        nav.addItem(new SideNavItem("Image List", ImageListView.class, LineAwesomeIcon.TH_LIST_SOLID.create()));
        nav.addItem(new SideNavItem("Checkout Form", CheckoutFormView.class, LineAwesomeIcon.CREDIT_CARD.create()));
        nav.addItem(
                new SideNavItem("Grid with Filters", GridwithFiltersView.class, LineAwesomeIcon.FILTER_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        
        dateLabel.setText(currentTime());
        layout.add(dateLabel);

        return layout;
    }

    private String currentTime() {
    	return LocalDateTime.now().format(LOCAL_DATE_TIME);
    }
    
    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
        dateLabel.setText(currentTime());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
