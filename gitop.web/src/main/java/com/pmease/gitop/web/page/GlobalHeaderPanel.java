package com.pmease.gitop.web.page;

import org.apache.shiro.SecurityUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.google.common.base.Optional;
import com.pmease.gitop.core.Gitop;
import com.pmease.gitop.core.manager.UserManager;
import com.pmease.gitop.model.User;
import com.pmease.gitop.model.permission.ObjectPermission;
import com.pmease.gitop.web.component.link.UserLink;
import com.pmease.gitop.web.page.account.RegisterPage;
import com.pmease.gitop.web.page.account.setting.profile.AccountProfilePage;
import com.pmease.gitop.web.page.admin.AdministrationOverviewPage;
import com.pmease.gitop.web.page.repository.settings.CreateRepositoryPage;
import com.pmease.gitop.web.shiro.LoginPage;
import com.pmease.gitop.web.shiro.LogoutPage;

@SuppressWarnings("serial")
public class GlobalHeaderPanel extends Panel {

	public GlobalHeaderPanel(String id) {
		super(id);
		
		add(new BookmarkablePageLink<Void>("homeLink", getApplication().getHomePage()));
		
		add(new BookmarkablePageLink<Void>("loginLink", LoginPage.class).setVisibilityAllowed(!isSignedIn()));
		add(new BookmarkablePageLink<Void>("registerLink", RegisterPage.class).setVisibilityAllowed(!isSignedIn()));
		add(new BookmarkablePageLink<Void>("logoutLink", LogoutPage.class).setVisibilityAllowed(isSignedIn()));
		
		if (isSignedIn()) {
			add(new UserLink("userlink", currentUser().get()));
			add(new BookmarkablePageLink<Void>("profileLink", 
												AccountProfilePage.class, 
												AccountProfilePage.newParams(currentUser().get())));
			
			add(new BookmarkablePageLink<Void>("newlink", CreateRepositoryPage.class));
			add(new BookmarkablePageLink<Void>("adminlink", AdministrationOverviewPage.class) {
				@Override
				protected void onConfigure() {
					setVisibilityAllowed(SecurityUtils.getSubject().isPermitted(ObjectPermission.ofSystemAdmin()));
				}
			});
		} else {
			add(new WebMarkupContainer("userlink").setVisibilityAllowed(false));
		}
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
	}
	
	protected boolean isSignedIn() {
		return currentUser().isPresent();
	}
	
	protected Optional<User> currentUser() {
        return Optional.<User>fromNullable(Gitop.getInstance(UserManager.class).getCurrent());
	}
}
