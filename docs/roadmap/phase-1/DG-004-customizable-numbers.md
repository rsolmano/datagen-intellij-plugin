# DG-004: Customizable Number Generator

**Status**: 🟢 Ready  
**Priority**: High  
**Effort**: M  
**Version**: v1.4.0  

## Description
Replace fixed number ranges with customizable number generation that allows users to specify min/max ranges and number types.

## User Story
As a developer, I want to generate numbers within custom ranges and formats so that I can create test data that fits my specific requirements instead of being limited to predefined ranges.

## Acceptance Criteria
- [ ] Create dialog for custom number range input
- [ ] Support integer and decimal number generation
- [ ] Allow negative numbers
- [ ] Support different number formats (decimal, hexadecimal, binary)
- [ ] Replace existing fixed range actions with configurable ones
- [ ] Remember last used settings
- [ ] Add validation for input ranges
- [ ] Support scientific notation option

## Technical Requirements
- Design and implement custom number generation dialog
- Create number generation engine with multiple format support
- Implement settings persistence for user preferences
- Add input validation and error handling
- Integrate with existing menu structure
- Maintain backward compatibility with existing actions

## Testing Requirements
- Unit tests for number generation with various settings
- Test dialog validation and error handling
- Test settings persistence across IDE sessions
- Verify different number formats (hex, binary, scientific)
- Test edge cases (negative numbers, decimal precision)
- UI tests for dialog interaction

## Documentation
- Update README.md with custom number generation
- Create demo showing dialog usage
- Document keyboard shortcuts
- Add examples of different number formats

## Dependencies
- IntelliJ Platform UI components
- Settings persistence API

## Migration Strategy
- Keep existing fixed-range actions for backward compatibility
- Add new custom number action as primary option
- Gradually deprecate fixed ranges in future versions

## Notes
- Dialog should be user-friendly with clear validation messages
- Consider adding presets for common ranges (1-100, 0-1, etc.)
- Ensure good performance for large number ranges
- Add tooltips explaining different number formats