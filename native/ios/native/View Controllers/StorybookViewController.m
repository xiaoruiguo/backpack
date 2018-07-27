//
//  StorybookViewController.m
//  Backpack Native
//
//  Created by Hugo Tunius on 26/07/2018.
//  Copyright © 2018 Facebook. All rights reserved.
//

#import "StorybookViewController.h"

#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>
#import <React/RCTI18nUtil.h>

@interface StorybookViewController ()
@end

@implementation StorybookViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    NSURL *jsCodeLocation;

    jsCodeLocation = [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index.ios" fallbackResource:nil];

    RCTRootView *rootView = [[RCTRootView alloc] initWithBundleURL:jsCodeLocation
                                                        moduleName:@"native"
                                                 initialProperties:nil
                                                    launchOptions:nil];
    rootView.backgroundColor = [[UIColor alloc] initWithRed:1.0f green:1.0f blue:1.0f alpha:1];

    [self.view addSubview:rootView];
    rootView.translatesAutoresizingMaskIntoConstraints = NO;
    UILayoutGuide *margins = self.view.layoutMarginsGuide;
    [self.view.leadingAnchor constraintEqualToAnchor:rootView.leadingAnchor].active = YES;
    [self.view.trailingAnchor constraintEqualToAnchor:rootView.trailingAnchor].active = YES;
    [margins.topAnchor constraintEqualToAnchor:rootView.topAnchor].active = YES;
    [margins.bottomAnchor constraintEqualToAnchor:rootView.bottomAnchor].active = YES;

    [[RCTI18nUtil sharedInstance] allowRTL:YES];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
