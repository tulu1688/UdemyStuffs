# Responsive layout
__Responsive Web design__ is the approach that suggests that design and development should respond to the user's behavior and environment based on screen size, platform and orientation. The practice consists of a mix of flexible grids and layouts, images and an intelligent use of CSS media queries
# Main components and Strategies
1. Fluid & Flexible layouts  
Add css styles for fluid width  
Use percentages instead of fixed widths  
2. Flexible images
3. Css3 media queries  
Example media query for multiple screen type  
> `<link rel="stylesheet" media="screen" href="style.css">`  
> `<link rel="stylesheet" media="only screen and (min-width: 320px) and (max-width: 568px)" href="mobile.css">`  
> `<link rel="stylesheet" media="only screen and (min-width: 768px) and (max-width: 980px)" href="tablet.css">  `

Inline media query syntax
> `@media(max-width: 600px){
  /*style go here*/
}`  
> `@media(min-width: 700px){
  /*style go here*/
}`  
> `@media only screen and (min-width: 320px) and (max-width: 568px){
  /*style go here*/
}`  
> `@media only screen and (min-width: 768px) and (max-width: 980px){
  /*style go here*/
}`  

# Responsive Layout Tools
## Design tool
### Graphic standalone tools
- Adobe Edge Reflow (for Responsive Web Design)
- Adobe Photoshop
- Adobe Fireworks
- Adobe Illustrator
### In browser tool
- [Easel](https://www.easel.io)
- [Froont](http://froont.com)
- [Responsivels](http://responsive.is)
- Grid System: Bootstrap, Less, Skeleton, Fluid Grids
## Wireframing
- [Wirefy](http://getwirefy.com)
- [Bootstrap PSDs](http://ghostlypixels.com/freebies/twitter-bootstrap-3-psd)
- [Jetstrap](http://jetstrap.com)
- [Responsive Wireframes](http://www.thismanslife.co.uk/projects/lab/responsivewireframe/#desktop)
- [Style Tiles](http://styletil.es/)
## Testing tool
- [Matt Kersley Responsive Design Testing Tool](http://mattkersley.com/responsive)
- Studiopress
- [Protofluid](http://protofluid.com)
- [Responsinator](http://springload.responsinator.com)
