package com.github.tsa6.piratecraftforumbanners.statistics;

import com.github.tsa6.piratecraftforumbanners.ImageUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class FAStatistic implements Statistic {
	
	private static final String fontAwesomeURL = "http://stats.piratemc.com/font-awesome/fonts/fontawesome-webfont.ttf?v=4.3.0";
	private static final Font fontAwesome;
	static {
		try {
			fontAwesome = Font.createFont(Font.TRUETYPE_FONT, FAStatistic.class.getClassLoader().getResourceAsStream("fontawesome-webfont.ttf"));
		} catch (FontFormatException | IOException ex) {
			throw new Error(ex);
		}
	}
	
	protected abstract char getCharacter();

	@Override
	public Image getIcon(int size) {
		return getIcon(size, getCharacter());
	}
	
	public static BufferedImage getIcon(int size, char character) {
		BufferedImage output = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		Graphics g = output.getGraphics();
		g.setColor(Color.BLACK);
		g.setFont(ImageUtils.determineFontSize(g, fontAwesome, character + "", size - 4, size - 4));
		ImageUtils.centerTextInArea(g, character + "", 2, 2, size - 4, size - 4);
		return output;
	}
	
	public static char translateToCharacter(String faName) {
		switch(faName) {
			case "fa-glass":
				return (int) 0xf000;

			case "fa-music":
				return (int) 0xf001;

			case "fa-search":
				return (int) 0xf002;

			case "fa-envelope-o":
				return (int) 0xf003;

			case "fa-heart":
				return (int) 0xf004;

			case "fa-star":
				return (int) 0xf005;

			case "fa-star-o":
				return (int) 0xf006;

			case "fa-user":
				return (int) 0xf007;

			case "fa-film":
				return (int) 0xf008;

			case "fa-th-large":
				return (int) 0xf009;

			case "fa-th":
				return (int) 0xf00a;

			case "fa-th-list":
				return (int) 0xf00b;

			case "fa-check":
				return (int) 0xf00c;

			case "fa-remove": case "fa-close": case "fa-times":
				return (int) 0xf00d;

			case "fa-search-plus":
				return (int) 0xf00e;

			case "fa-search-minus":
				return (int) 0xf010;

			case "fa-power-off":
				return (int) 0xf011;

			case "fa-signal":
				return (int) 0xf012;

			case "fa-gear": case "fa-cog":
				return (int) 0xf013;

			case "fa-trash-o":
				return (int) 0xf014;

			case "fa-home":
				return (int) 0xf015;

			case "fa-file-o":
				return (int) 0xf016;

			case "fa-clock-o":
				return (int) 0xf017;

			case "fa-road":
				return (int) 0xf018;

			case "fa-download":
				return (int) 0xf019;

			case "fa-arrow-circle-o-down":
				return (int) 0xf01a;

			case "fa-arrow-circle-o-up":
				return (int) 0xf01b;

			case "fa-inbox":
				return (int) 0xf01c;

			case "fa-play-circle-o":
				return (int) 0xf01d;

			case "fa-rotate-right": case "fa-repeat":
				return (int) 0xf01e;

			case "fa-refresh":
				return (int) 0xf021;

			case "fa-list-alt":
				return (int) 0xf022;

			case "fa-lock":
				return (int) 0xf023;

			case "fa-flag":
				return (int) 0xf024;

			case "fa-headphones":
				return (int) 0xf025;

			case "fa-volume-off":
				return (int) 0xf026;

			case "fa-volume-down":
				return (int) 0xf027;

			case "fa-volume-up":
				return (int) 0xf028;

			case "fa-qrcode":
				return (int) 0xf029;

			case "fa-barcode":
				return (int) 0xf02a;

			case "fa-tag":
				return (int) 0xf02b;

			case "fa-tags":
				return (int) 0xf02c;

			case "fa-book":
				return (int) 0xf02d;

			case "fa-bookmark":
				return (int) 0xf02e;

			case "fa-print":
				return (int) 0xf02f;

			case "fa-camera":
				return (int) 0xf030;

			case "fa-font":
				return (int) 0xf031;

			case "fa-bold":
				return (int) 0xf032;

			case "fa-italic":
				return (int) 0xf033;

			case "fa-text-height":
				return (int) 0xf034;

			case "fa-text-width":
				return (int) 0xf035;

			case "fa-align-left":
				return (int) 0xf036;

			case "fa-align-center":
				return (int) 0xf037;

			case "fa-align-right":
				return (int) 0xf038;

			case "fa-align-justify":
				return (int) 0xf039;

			case "fa-list":
				return (int) 0xf03a;

			case "fa-dedent": case "fa-outdent":
				return (int) 0xf03b;

			case "fa-indent":
				return (int) 0xf03c;

			case "fa-video-camera":
				return (int) 0xf03d;

			case "fa-photo": case "fa-image": case "fa-picture-o":
				return (int) 0xf03e;

			case "fa-pencil":
				return (int) 0xf040;

			case "fa-map-marker":
				return (int) 0xf041;

			case "fa-adjust":
				return (int) 0xf042;

			case "fa-tint":
				return (int) 0xf043;

			case "fa-edit": case "fa-pencil-square-o":
				return (int) 0xf044;

			case "fa-share-square-o":
				return (int) 0xf045;

			case "fa-check-square-o":
				return (int) 0xf046;

			case "fa-arrows":
				return (int) 0xf047;

			case "fa-step-backward":
				return (int) 0xf048;

			case "fa-fast-backward":
				return (int) 0xf049;

			case "fa-backward":
				return (int) 0xf04a;

			case "fa-play":
				return (int) 0xf04b;

			case "fa-pause":
				return (int) 0xf04c;

			case "fa-stop":
				return (int) 0xf04d;

			case "fa-forward":
				return (int) 0xf04e;

			case "fa-fast-forward":
				return (int) 0xf050;

			case "fa-step-forward":
				return (int) 0xf051;

			case "fa-eject":
				return (int) 0xf052;

			case "fa-chevron-left":
				return (int) 0xf053;

			case "fa-chevron-right":
				return (int) 0xf054;

			case "fa-plus-circle":
				return (int) 0xf055;

			case "fa-minus-circle":
				return (int) 0xf056;

			case "fa-times-circle":
				return (int) 0xf057;

			case "fa-check-circle":
				return (int) 0xf058;

			case "fa-question-circle":
				return (int) 0xf059;

			case "fa-info-circle":
				return (int) 0xf05a;

			case "fa-crosshairs":
				return (int) 0xf05b;

			case "fa-times-circle-o":
				return (int) 0xf05c;

			case "fa-check-circle-o":
				return (int) 0xf05d;

			case "fa-ban":
				return (int) 0xf05e;

			case "fa-arrow-left":
				return (int) 0xf060;

			case "fa-arrow-right":
				return (int) 0xf061;

			case "fa-arrow-up":
				return (int) 0xf062;

			case "fa-arrow-down":
				return (int) 0xf063;

			case "fa-mail-forward": case "fa-share":
				return (int) 0xf064;

			case "fa-expand":
				return (int) 0xf065;

			case "fa-compress":
				return (int) 0xf066;

			case "fa-plus":
				return (int) 0xf067;

			case "fa-minus":
				return (int) 0xf068;

			case "fa-asterisk":
				return (int) 0xf069;

			case "fa-exclamation-circle":
				return (int) 0xf06a;

			case "fa-gift":
				return (int) 0xf06b;

			case "fa-leaf":
				return (int) 0xf06c;

			case "fa-fire":
				return (int) 0xf06d;

			case "fa-eye":
				return (int) 0xf06e;

			case "fa-eye-slash":
				return (int) 0xf070;

			case "fa-warning": case "fa-exclamation-triangle":
				return (int) 0xf071;

			case "fa-plane":
				return (int) 0xf072;

			case "fa-calendar":
				return (int) 0xf073;

			case "fa-random":
				return (int) 0xf074;

			case "fa-comment":
				return (int) 0xf075;

			case "fa-magnet":
				return (int) 0xf076;

			case "fa-chevron-up":
				return (int) 0xf077;

			case "fa-chevron-down":
				return (int) 0xf078;

			case "fa-retweet":
				return (int) 0xf079;

			case "fa-shopping-cart":
				return (int) 0xf07a;

			case "fa-folder":
				return (int) 0xf07b;

			case "fa-folder-open":
				return (int) 0xf07c;

			case "fa-arrows-v":
				return (int) 0xf07d;

			case "fa-arrows-h":
				return (int) 0xf07e;

			case "fa-bar-chart-o": case "fa-bar-chart":
				return (int) 0xf080;

			case "fa-twitter-square":
				return (int) 0xf081;

			case "fa-facebook-square":
				return (int) 0xf082;

			case "fa-camera-retro":
				return (int) 0xf083;

			case "fa-key":
				return (int) 0xf084;

			case "fa-gears": case "fa-cogs":
				return (int) 0xf085;

			case "fa-comments":
				return (int) 0xf086;

			case "fa-thumbs-o-up":
				return (int) 0xf087;

			case "fa-thumbs-o-down":
				return (int) 0xf088;

			case "fa-star-half":
				return (int) 0xf089;

			case "fa-heart-o":
				return (int) 0xf08a;

			case "fa-sign-out":
				return (int) 0xf08b;

			case "fa-linkedin-square":
				return (int) 0xf08c;

			case "fa-thumb-tack":
				return (int) 0xf08d;

			case "fa-external-link":
				return (int) 0xf08e;

			case "fa-sign-in":
				return (int) 0xf090;

			case "fa-trophy":
				return (int) 0xf091;

			case "fa-github-square":
				return (int) 0xf092;

			case "fa-upload":
				return (int) 0xf093;

			case "fa-lemon-o":
				return (int) 0xf094;

			case "fa-phone":
				return (int) 0xf095;

			case "fa-square-o":
				return (int) 0xf096;

			case "fa-bookmark-o":
				return (int) 0xf097;

			case "fa-phone-square":
				return (int) 0xf098;

			case "fa-twitter":
				return (int) 0xf099;

			case "fa-facebook-f": case "fa-facebook":
				return (int) 0xf09a;

			case "fa-github":
				return (int) 0xf09b;

			case "fa-unlock":
				return (int) 0xf09c;

			case "fa-credit-card":
				return (int) 0xf09d;

			case "fa-rss":
				return (int) 0xf09e;

			case "fa-hdd-o":
				return (int) 0xf0a0;

			case "fa-bullhorn":
				return (int) 0xf0a1;

			case "fa-bell":
				return (int) 0xf0f3;

			case "fa-certificate":
				return (int) 0xf0a3;

			case "fa-hand-o-right":
				return (int) 0xf0a4;

			case "fa-hand-o-left":
				return (int) 0xf0a5;

			case "fa-hand-o-up":
				return (int) 0xf0a6;

			case "fa-hand-o-down":
				return (int) 0xf0a7;

			case "fa-arrow-circle-left":
				return (int) 0xf0a8;

			case "fa-arrow-circle-right":
				return (int) 0xf0a9;

			case "fa-arrow-circle-up":
				return (int) 0xf0aa;

			case "fa-arrow-circle-down":
				return (int) 0xf0ab;

			case "fa-globe":
				return (int) 0xf0ac;

			case "fa-wrench":
				return (int) 0xf0ad;

			case "fa-tasks":
				return (int) 0xf0ae;

			case "fa-filter":
				return (int) 0xf0b0;

			case "fa-briefcase":
				return (int) 0xf0b1;

			case "fa-arrows-alt":
				return (int) 0xf0b2;

			case "fa-group": case "fa-users":
				return (int) 0xf0c0;

			case "fa-chain": case "fa-link":
				return (int) 0xf0c1;

			case "fa-cloud":
				return (int) 0xf0c2;

			case "fa-flask":
				return (int) 0xf0c3;

			case "fa-cut": case "fa-scissors":
				return (int) 0xf0c4;

			case "fa-copy": case "fa-files-o":
				return (int) 0xf0c5;

			case "fa-paperclip":
				return (int) 0xf0c6;

			case "fa-save": case "fa-floppy-o":
				return (int) 0xf0c7;

			case "fa-square":
				return (int) 0xf0c8;

			case "fa-navicon": case "fa-reorder": case "fa-bars":
				return (int) 0xf0c9;

			case "fa-list-ul":
				return (int) 0xf0ca;

			case "fa-list-ol":
				return (int) 0xf0cb;

			case "fa-strikethrough":
				return (int) 0xf0cc;

			case "fa-underline":
				return (int) 0xf0cd;

			case "fa-table":
				return (int) 0xf0ce;

			case "fa-magic":
				return (int) 0xf0d0;

			case "fa-truck":
				return (int) 0xf0d1;

			case "fa-pinterest":
				return (int) 0xf0d2;

			case "fa-pinterest-square":
				return (int) 0xf0d3;

			case "fa-google-plus-square":
				return (int) 0xf0d4;

			case "fa-google-plus":
				return (int) 0xf0d5;

			case "fa-money":
				return (int) 0xf0d6;

			case "fa-caret-down":
				return (int) 0xf0d7;

			case "fa-caret-up":
				return (int) 0xf0d8;

			case "fa-caret-left":
				return (int) 0xf0d9;

			case "fa-caret-right":
				return (int) 0xf0da;

			case "fa-columns":
				return (int) 0xf0db;

			case "fa-unsorted": case "fa-sort":
				return (int) 0xf0dc;

			case "fa-sort-down": case "fa-sort-desc":
				return (int) 0xf0dd;

			case "fa-sort-up": case "fa-sort-asc":
				return (int) 0xf0de;

			case "fa-envelope":
				return (int) 0xf0e0;

			case "fa-linkedin":
				return (int) 0xf0e1;

			case "fa-rotate-left": case "fa-undo":
				return (int) 0xf0e2;

			case "fa-legal": case "fa-gavel":
				return (int) 0xf0e3;

			case "fa-dashboard": case "fa-tachometer":
				return (int) 0xf0e4;

			case "fa-comment-o":
				return (int) 0xf0e5;

			case "fa-comments-o":
				return (int) 0xf0e6;

			case "fa-flash": case "fa-bolt":
				return (int) 0xf0e7;

			case "fa-sitemap":
				return (int) 0xf0e8;

			case "fa-umbrella":
				return (int) 0xf0e9;

			case "fa-paste": case "fa-clipboard":
				return (int) 0xf0ea;

			case "fa-lightbulb-o":
				return (int) 0xf0eb;

			case "fa-exchange":
				return (int) 0xf0ec;

			case "fa-cloud-download":
				return (int) 0xf0ed;

			case "fa-cloud-upload":
				return (int) 0xf0ee;

			case "fa-user-md":
				return (int) 0xf0f0;

			case "fa-stethoscope":
				return (int) 0xf0f1;

			case "fa-suitcase":
				return (int) 0xf0f2;

			case "fa-bell-o":
				return (int) 0xf0a2;

			case "fa-coffee":
				return (int) 0xf0f4;

			case "fa-cutlery":
				return (int) 0xf0f5;

			case "fa-file-text-o":
				return (int) 0xf0f6;

			case "fa-building-o":
				return (int) 0xf0f7;

			case "fa-hospital-o":
				return (int) 0xf0f8;

			case "fa-ambulance":
				return (int) 0xf0f9;

			case "fa-medkit":
				return (int) 0xf0fa;

			case "fa-fighter-jet":
				return (int) 0xf0fb;

			case "fa-beer":
				return (int) 0xf0fc;

			case "fa-h-square":
				return (int) 0xf0fd;

			case "fa-plus-square":
				return (int) 0xf0fe;

			case "fa-angle-double-left":
				return (int) 0xf100;

			case "fa-angle-double-right":
				return (int) 0xf101;

			case "fa-angle-double-up":
				return (int) 0xf102;

			case "fa-angle-double-down":
				return (int) 0xf103;

			case "fa-angle-left":
				return (int) 0xf104;

			case "fa-angle-right":
				return (int) 0xf105;

			case "fa-angle-up":
				return (int) 0xf106;

			case "fa-angle-down":
				return (int) 0xf107;

			case "fa-desktop":
				return (int) 0xf108;

			case "fa-laptop":
				return (int) 0xf109;

			case "fa-tablet":
				return (int) 0xf10a;

			case "fa-mobile-phone": case "fa-mobile":
				return (int) 0xf10b;

			case "fa-circle-o":
				return (int) 0xf10c;

			case "fa-quote-left":
				return (int) 0xf10d;

			case "fa-quote-right":
				return (int) 0xf10e;

			case "fa-spinner":
				return (int) 0xf110;

			case "fa-circle":
				return (int) 0xf111;

			case "fa-mail-reply": case "fa-reply":
				return (int) 0xf112;

			case "fa-github-alt":
				return (int) 0xf113;

			case "fa-folder-o":
				return (int) 0xf114;

			case "fa-folder-open-o":
				return (int) 0xf115;

			case "fa-smile-o":
				return (int) 0xf118;

			case "fa-frown-o":
				return (int) 0xf119;

			case "fa-meh-o":
				return (int) 0xf11a;

			case "fa-gamepad":
				return (int) 0xf11b;

			case "fa-keyboard-o":
				return (int) 0xf11c;

			case "fa-flag-o":
				return (int) 0xf11d;

			case "fa-flag-checkered":
				return (int) 0xf11e;

			case "fa-terminal":
				return (int) 0xf120;

			case "fa-code":
				return (int) 0xf121;

			case "fa-mail-reply-all": case "fa-reply-all":
				return (int) 0xf122;

			case "fa-star-half-empty": case "fa-star-half-full": case "fa-star-half-o":
				return (int) 0xf123;

			case "fa-location-arrow":
				return (int) 0xf124;

			case "fa-crop":
				return (int) 0xf125;

			case "fa-code-fork":
				return (int) 0xf126;

			case "fa-unlink": case "fa-chain-broken":
				return (int) 0xf127;

			case "fa-question":
				return (int) 0xf128;

			case "fa-info":
				return (int) 0xf129;

			case "fa-exclamation":
				return (int) 0xf12a;

			case "fa-superscript":
				return (int) 0xf12b;

			case "fa-subscript":
				return (int) 0xf12c;

			case "fa-eraser":
				return (int) 0xf12d;

			case "fa-puzzle-piece":
				return (int) 0xf12e;

			case "fa-microphone":
				return (int) 0xf130;

			case "fa-microphone-slash":
				return (int) 0xf131;

			case "fa-shield":
				return (int) 0xf132;

			case "fa-calendar-o":
				return (int) 0xf133;

			case "fa-fire-extinguisher":
				return (int) 0xf134;

			case "fa-rocket":
				return (int) 0xf135;

			case "fa-maxcdn":
				return (int) 0xf136;

			case "fa-chevron-circle-left":
				return (int) 0xf137;

			case "fa-chevron-circle-right":
				return (int) 0xf138;

			case "fa-chevron-circle-up":
				return (int) 0xf139;

			case "fa-chevron-circle-down":
				return (int) 0xf13a;

			case "fa-html5":
				return (int) 0xf13b;

			case "fa-css3":
				return (int) 0xf13c;

			case "fa-anchor":
				return (int) 0xf13d;

			case "fa-unlock-alt":
				return (int) 0xf13e;

			case "fa-bullseye":
				return (int) 0xf140;

			case "fa-ellipsis-h":
				return (int) 0xf141;

			case "fa-ellipsis-v":
				return (int) 0xf142;

			case "fa-rss-square":
				return (int) 0xf143;

			case "fa-play-circle":
				return (int) 0xf144;

			case "fa-ticket":
				return (int) 0xf145;

			case "fa-minus-square":
				return (int) 0xf146;

			case "fa-minus-square-o":
				return (int) 0xf147;

			case "fa-level-up":
				return (int) 0xf148;

			case "fa-level-down":
				return (int) 0xf149;

			case "fa-check-square":
				return (int) 0xf14a;

			case "fa-pencil-square":
				return (int) 0xf14b;

			case "fa-external-link-square":
				return (int) 0xf14c;

			case "fa-share-square":
				return (int) 0xf14d;

			case "fa-compass":
				return (int) 0xf14e;

			case "fa-toggle-down": case "fa-caret-square-o-down":
				return (int) 0xf150;

			case "fa-toggle-up": case "fa-caret-square-o-up":
				return (int) 0xf151;

			case "fa-toggle-right": case "fa-caret-square-o-right":
				return (int) 0xf152;

			case "fa-euro": case "fa-eur":
				return (int) 0xf153;

			case "fa-gbp":
				return (int) 0xf154;

			case "fa-dollar": case "fa-usd":
				return (int) 0xf155;

			case "fa-rupee": case "fa-inr":
				return (int) 0xf156;

			case "fa-cny": case "fa-rmb": case "fa-yen": case "fa-jpy":
				return (int) 0xf157;

			case "fa-ruble": case "fa-rouble": case "fa-rub":
				return (int) 0xf158;

			case "fa-won": case "fa-krw":
				return (int) 0xf159;

			case "fa-bitcoin": case "fa-btc":
				return (int) 0xf15a;

			case "fa-file":
				return (int) 0xf15b;

			case "fa-file-text":
				return (int) 0xf15c;

			case "fa-sort-alpha-asc":
				return (int) 0xf15d;

			case "fa-sort-alpha-desc":
				return (int) 0xf15e;

			case "fa-sort-amount-asc":
				return (int) 0xf160;

			case "fa-sort-amount-desc":
				return (int) 0xf161;

			case "fa-sort-numeric-asc":
				return (int) 0xf162;

			case "fa-sort-numeric-desc":
				return (int) 0xf163;

			case "fa-thumbs-up":
				return (int) 0xf164;

			case "fa-thumbs-down":
				return (int) 0xf165;

			case "fa-youtube-square":
				return (int) 0xf166;

			case "fa-youtube":
				return (int) 0xf167;

			case "fa-xing":
				return (int) 0xf168;

			case "fa-xing-square":
				return (int) 0xf169;

			case "fa-youtube-play":
				return (int) 0xf16a;

			case "fa-dropbox":
				return (int) 0xf16b;

			case "fa-stack-overflow":
				return (int) 0xf16c;

			case "fa-instagram":
				return (int) 0xf16d;

			case "fa-flickr":
				return (int) 0xf16e;

			case "fa-adn":
				return (int) 0xf170;

			case "fa-bitbucket":
				return (int) 0xf171;

			case "fa-bitbucket-square":
				return (int) 0xf172;

			case "fa-tumblr":
				return (int) 0xf173;

			case "fa-tumblr-square":
				return (int) 0xf174;

			case "fa-long-arrow-down":
				return (int) 0xf175;

			case "fa-long-arrow-up":
				return (int) 0xf176;

			case "fa-long-arrow-left":
				return (int) 0xf177;

			case "fa-long-arrow-right":
				return (int) 0xf178;

			case "fa-apple":
				return (int) 0xf179;

			case "fa-windows":
				return (int) 0xf17a;

			case "fa-android":
				return (int) 0xf17b;

			case "fa-linux":
				return (int) 0xf17c;

			case "fa-dribbble":
				return (int) 0xf17d;

			case "fa-skype":
				return (int) 0xf17e;

			case "fa-foursquare":
				return (int) 0xf180;

			case "fa-trello":
				return (int) 0xf181;

			case "fa-female":
				return (int) 0xf182;

			case "fa-male":
				return (int) 0xf183;

			case "fa-gittip": case "fa-gratipay":
				return (int) 0xf184;

			case "fa-sun-o":
				return (int) 0xf185;

			case "fa-moon-o":
				return (int) 0xf186;

			case "fa-archive":
				return (int) 0xf187;

			case "fa-bug":
				return (int) 0xf188;

			case "fa-vk":
				return (int) 0xf189;

			case "fa-weibo":
				return (int) 0xf18a;

			case "fa-renren":
				return (int) 0xf18b;

			case "fa-pagelines":
				return (int) 0xf18c;

			case "fa-stack-exchange":
				return (int) 0xf18d;

			case "fa-arrow-circle-o-right":
				return (int) 0xf18e;

			case "fa-arrow-circle-o-left":
				return (int) 0xf190;

			case "fa-toggle-left": case "fa-caret-square-o-left":
				return (int) 0xf191;

			case "fa-dot-circle-o":
				return (int) 0xf192;

			case "fa-wheelchair":
				return (int) 0xf193;

			case "fa-vimeo-square":
				return (int) 0xf194;

			case "fa-turkish-lira": case "fa-try":
				return (int) 0xf195;

			case "fa-plus-square-o":
				return (int) 0xf196;

			case "fa-space-shuttle":
				return (int) 0xf197;

			case "fa-slack":
				return (int) 0xf198;

			case "fa-envelope-square":
				return (int) 0xf199;

			case "fa-wordpress":
				return (int) 0xf19a;

			case "fa-openid":
				return (int) 0xf19b;

			case "fa-institution": case "fa-bank": case "fa-university":
				return (int) 0xf19c;

			case "fa-mortar-board": case "fa-graduation-cap":
				return (int) 0xf19d;

			case "fa-yahoo":
				return (int) 0xf19e;

			case "fa-google":
				return (int) 0xf1a0;

			case "fa-reddit":
				return (int) 0xf1a1;

			case "fa-reddit-square":
				return (int) 0xf1a2;

			case "fa-stumbleupon-circle":
				return (int) 0xf1a3;

			case "fa-stumbleupon":
				return (int) 0xf1a4;

			case "fa-delicious":
				return (int) 0xf1a5;

			case "fa-digg":
				return (int) 0xf1a6;

			case "fa-pied-piper":
				return (int) 0xf1a7;

			case "fa-pied-piper-alt":
				return (int) 0xf1a8;

			case "fa-drupal":
				return (int) 0xf1a9;

			case "fa-joomla":
				return (int) 0xf1aa;

			case "fa-language":
				return (int) 0xf1ab;

			case "fa-fax":
				return (int) 0xf1ac;

			case "fa-building":
				return (int) 0xf1ad;

			case "fa-child":
				return (int) 0xf1ae;

			case "fa-paw":
				return (int) 0xf1b0;

			case "fa-spoon":
				return (int) 0xf1b1;

			case "fa-cube":
				return (int) 0xf1b2;

			case "fa-cubes":
				return (int) 0xf1b3;

			case "fa-behance":
				return (int) 0xf1b4;

			case "fa-behance-square":
				return (int) 0xf1b5;

			case "fa-steam":
				return (int) 0xf1b6;

			case "fa-steam-square":
				return (int) 0xf1b7;

			case "fa-recycle":
				return (int) 0xf1b8;

			case "fa-automobile": case "fa-car":
				return (int) 0xf1b9;

			case "fa-cab": case "fa-taxi":
				return (int) 0xf1ba;

			case "fa-tree":
				return (int) 0xf1bb;

			case "fa-spotify":
				return (int) 0xf1bc;

			case "fa-deviantart":
				return (int) 0xf1bd;

			case "fa-soundcloud":
				return (int) 0xf1be;

			case "fa-database":
				return (int) 0xf1c0;

			case "fa-file-pdf-o":
				return (int) 0xf1c1;

			case "fa-file-word-o":
				return (int) 0xf1c2;

			case "fa-file-excel-o":
				return (int) 0xf1c3;

			case "fa-file-powerpoint-o":
				return (int) 0xf1c4;

			case "fa-file-photo-o": case "fa-file-picture-o": case "fa-file-image-o":
				return (int) 0xf1c5;

			case "fa-file-zip-o": case "fa-file-archive-o":
				return (int) 0xf1c6;

			case "fa-file-sound-o": case "fa-file-audio-o":
				return (int) 0xf1c7;

			case "fa-file-movie-o": case "fa-file-video-o":
				return (int) 0xf1c8;

			case "fa-file-code-o":
				return (int) 0xf1c9;

			case "fa-vine":
				return (int) 0xf1ca;

			case "fa-codepen":
				return (int) 0xf1cb;

			case "fa-jsfiddle":
				return (int) 0xf1cc;

			case "fa-life-bouy": case "fa-life-buoy": case "fa-life-saver": case "fa-support": case "fa-life-ring":
				return (int) 0xf1cd;

			case "fa-circle-o-notch":
				return (int) 0xf1ce;

			case "fa-ra": case "fa-rebel":
				return (int) 0xf1d0;

			case "fa-ge": case "fa-empire":
				return (int) 0xf1d1;

			case "fa-git-square":
				return (int) 0xf1d2;

			case "fa-git":
				return (int) 0xf1d3;

			case "fa-hacker-news":
				return (int) 0xf1d4;

			case "fa-tencent-weibo":
				return (int) 0xf1d5;

			case "fa-qq":
				return (int) 0xf1d6;

			case "fa-wechat": case "fa-weixin":
				return (int) 0xf1d7;

			case "fa-send": case "fa-paper-plane":
				return (int) 0xf1d8;

			case "fa-send-o": case "fa-paper-plane-o":
				return (int) 0xf1d9;

			case "fa-history":
				return (int) 0xf1da;

			case "fa-genderless": case "fa-circle-thin":
				return (int) 0xf1db;

			case "fa-header":
				return (int) 0xf1dc;

			case "fa-paragraph":
				return (int) 0xf1dd;

			case "fa-sliders":
				return (int) 0xf1de;

			case "fa-share-alt":
				return (int) 0xf1e0;

			case "fa-share-alt-square":
				return (int) 0xf1e1;

			case "fa-bomb":
				return (int) 0xf1e2;

			case "fa-soccer-ball-o": case "fa-futbol-o":
				return (int) 0xf1e3;

			case "fa-tty":
				return (int) 0xf1e4;

			case "fa-binoculars":
				return (int) 0xf1e5;

			case "fa-plug":
				return (int) 0xf1e6;

			case "fa-slideshare":
				return (int) 0xf1e7;

			case "fa-twitch":
				return (int) 0xf1e8;

			case "fa-yelp":
				return (int) 0xf1e9;

			case "fa-newspaper-o":
				return (int) 0xf1ea;

			case "fa-wifi":
				return (int) 0xf1eb;

			case "fa-calculator":
				return (int) 0xf1ec;

			case "fa-paypal":
				return (int) 0xf1ed;

			case "fa-google-wallet":
				return (int) 0xf1ee;

			case "fa-cc-visa":
				return (int) 0xf1f0;

			case "fa-cc-mastercard":
				return (int) 0xf1f1;

			case "fa-cc-discover":
				return (int) 0xf1f2;

			case "fa-cc-amex":
				return (int) 0xf1f3;

			case "fa-cc-paypal":
				return (int) 0xf1f4;

			case "fa-cc-stripe":
				return (int) 0xf1f5;

			case "fa-bell-slash":
				return (int) 0xf1f6;

			case "fa-bell-slash-o":
				return (int) 0xf1f7;

			case "fa-trash":
				return (int) 0xf1f8;

			case "fa-copyright":
				return (int) 0xf1f9;

			case "fa-at":
				return (int) 0xf1fa;

			case "fa-eyedropper":
				return (int) 0xf1fb;

			case "fa-paint-brush":
				return (int) 0xf1fc;

			case "fa-birthday-cake":
				return (int) 0xf1fd;

			case "fa-area-chart":
				return (int) 0xf1fe;

			case "fa-pie-chart":
				return (int) 0xf200;

			case "fa-line-chart":
				return (int) 0xf201;

			case "fa-lastfm":
				return (int) 0xf202;

			case "fa-lastfm-square":
				return (int) 0xf203;

			case "fa-toggle-off":
				return (int) 0xf204;

			case "fa-toggle-on":
				return (int) 0xf205;

			case "fa-bicycle":
				return (int) 0xf206;

			case "fa-bus":
				return (int) 0xf207;

			case "fa-ioxhost":
				return (int) 0xf208;

			case "fa-angellist":
				return (int) 0xf209;

			case "fa-cc":
				return (int) 0xf20a;

			case "fa-shekel": case "fa-sheqel": case "fa-ils":
				return (int) 0xf20b;

			case "fa-meanpath":
				return (int) 0xf20c;

			case "fa-buysellads":
				return (int) 0xf20d;

			case "fa-connectdevelop":
				return (int) 0xf20e;

			case "fa-dashcube":
				return (int) 0xf210;

			case "fa-forumbee":
				return (int) 0xf211;

			case "fa-leanpub":
				return (int) 0xf212;

			case "fa-sellsy":
				return (int) 0xf213;

			case "fa-shirtsinbulk":
				return (int) 0xf214;

			case "fa-simplybuilt":
				return (int) 0xf215;

			case "fa-skyatlas":
				return (int) 0xf216;

			case "fa-cart-plus":
				return (int) 0xf217;

			case "fa-cart-arrow-down":
				return (int) 0xf218;

			case "fa-diamond":
				return (int) 0xf219;

			case "fa-ship":
				return (int) 0xf21a;

			case "fa-user-secret":
				return (int) 0xf21b;

			case "fa-motorcycle":
				return (int) 0xf21c;

			case "fa-street-view":
				return (int) 0xf21d;

			case "fa-heartbeat":
				return (int) 0xf21e;

			case "fa-venus":
				return (int) 0xf221;

			case "fa-mars":
				return (int) 0xf222;

			case "fa-mercury":
				return (int) 0xf223;

			case "fa-transgender":
				return (int) 0xf224;

			case "fa-transgender-alt":
				return (int) 0xf225;

			case "fa-venus-double":
				return (int) 0xf226;

			case "fa-mars-double":
				return (int) 0xf227;

			case "fa-venus-mars":
				return (int) 0xf228;

			case "fa-mars-stroke":
				return (int) 0xf229;

			case "fa-mars-stroke-v":
				return (int) 0xf22a;

			case "fa-mars-stroke-h":
				return (int) 0xf22b;

			case "fa-neuter":
				return (int) 0xf22c;

			case "fa-facebook-official":
				return (int) 0xf230;

			case "fa-pinterest-p":
				return (int) 0xf231;

			case "fa-whatsapp":
				return (int) 0xf232;

			case "fa-server":
				return (int) 0xf233;

			case "fa-user-plus":
				return (int) 0xf234;

			case "fa-user-times":
				return (int) 0xf235;

			case "fa-hotel": case "fa-bed":
				return (int) 0xf236;

			case "fa-viacoin":
				return (int) 0xf237;

			case "fa-train":
				return (int) 0xf238;

			case "fa-subway":
				return (int) 0xf239;

			case "fa-medium":
				return (int) 0xf23a;

			default:
				return '?';
		}
	}
	
}
