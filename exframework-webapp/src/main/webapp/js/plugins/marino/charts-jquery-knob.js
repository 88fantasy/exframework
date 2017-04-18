/**
 * @author Batch Themes Ltd.
 */
(function() {
    'use strict';
    $(function() {
        var config = $.localStorage.get('config');
        $('body').attr('data-layout', config.layout);
        $('body').attr('data-palette', config.theme);
        $('body').attr('data-direction', config.direction);
        var colors = config.colors;
        var palette = config.palette;
        $('.knob-success').each(function() {
            $(this).attr('data-fgColor', colors.success);
            $(this).attr('data-inputColor', palette.textColor);
            $(this).attr('data-bgColor', palette.oddColor);
        }).knob();
        $('.knob-warning').each(function() {
            $(this).attr('data-fgColor', colors.warning);
            $(this).attr('data-inputColor', palette.textColor);
            $(this).attr('data-bgColor', palette.oddColor);
        }).knob();
        $('.knob-danger').each(function() {
            $(this).attr('data-fgColor', colors.danger);
            $(this).attr('data-inputColor', palette.textColor);
            $(this).attr('data-bgColor', palette.oddColor);
        }).knob();
        $('.knob-info').each(function() {
            $(this).attr('data-fgColor', colors.info);
            $(this).attr('data-inputColor', palette.textColor);
            $(this).attr('data-bgColor', palette.oddColor);
        }).knob();
        //combined knobs
        $('.knob-success-warning').each(function() {
            $(this).attr('data-fgColor', colors.success);
            $(this).attr('data-inputColor', colors.warning);
            $(this).attr('data-bgColor', colors.warning);
        }).knob();
        $('.knob-warning-danger').each(function() {
            $(this).attr('data-fgColor', colors.warning);
            $(this).attr('data-inputColor', colors.danger);
            $(this).attr('data-bgColor', colors.danger);
        }).knob();
        $('.knob-danger-success').each(function() {
            $(this).attr('data-fgColor', colors.danger);
            $(this).attr('data-inputColor', colors.success);
            $(this).attr('data-bgColor', colors.success);
        }).knob();
        $('.knob-info-warning').each(function() {
            $(this).attr('data-fgColor', colors.info);
            $(this).attr('data-inputColor', colors.warning);
            $(this).attr('data-bgColor', colors.warning);
        }).knob();
    });
})();
