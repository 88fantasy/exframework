(window.webpackJsonp=window.webpackJsonp||[]).push([[2],{"/hEp":function(W,f,e){"use strict";Object.defineProperty(f,"__esModule",{value:!0}),f.default=void 0;var N=A(e("CrYe"));function A(B){return B&&B.__esModule?B:{default:B}}var C=N;f.default=C,W.exports=C},"15/o":function(W,f,e){},AOa7:function(W,f,e){},CrYe:function(W,f,e){"use strict";var N=e("TqRt"),A=e("284h");Object.defineProperty(f,"__esModule",{value:!0}),f.default=void 0;var C=A(e("q1tI")),B=N(e("r4ZK")),m=N(e("KQxl")),S=function(M,j){return C.createElement(m.default,Object.assign({},M,{ref:j,icon:B.default}))};S.displayName="ArrowRightOutlined";var O=C.forwardRef(S);f.default=O},DnfT:function(W,f,e){},FRQA:function(W,f,e){"use strict";var N=e("GNNt"),A=e("wEI+"),C=e("DnfT"),B=e.n(C),m=e("q1tI"),S=e.n(m),O=e("TSYQ"),Q=e.n(O),M=e("jYQm"),j=function(T){var R=Object(m.useContext)(M.a),Z=T.children,x=T.contentWidth,k=T.className,V=T.style,h=Object(m.useContext)(A.b.ConfigContext),d=h.getPrefixCls,g=T.prefixCls||d("pro"),y=x||R.contentWidth,U="".concat(g,"-grid-content");return S.a.createElement("div",{className:Q()(U,k,{wide:y==="Fixed"}),style:V},S.a.createElement("div",{className:"".concat(g,"-grid-content-children")},Z))};f.a=j},PEeC:function(W,f,e){"use strict";e.d(f,"a",function(){return B}),e.d(f,"b",function(){return m});var N=e("RIqP"),A=e.n(N),C=e("wgJM");function B(S){var O,Q=function(I){return function(){O=null,S.apply(void 0,A()(I))}},M=function(){if(O==null){for(var I=arguments.length,T=new Array(I),R=0;R<I;R++)T[R]=arguments[R];O=Object(C.a)(Q(T))}};return M.cancel=function(){return C.a.cancel(O)},M}function m(){return function(O,Q,M){var j=M.value,I=!1;return{configurable:!0,get:function(){if(I||this===O.prototype||this.hasOwnProperty(Q))return j;var R=B(j.bind(this));return I=!0,Object.defineProperty(this,Q,{value:R,configurable:!0,writable:!0}),I=!1,R}}}}},VNzZ:function(W,f,e){"use strict";var N=e("pVnL"),A=e.n(N),C=e("lSNA"),B=e.n(C),m=e("lwsE"),S=e.n(m),O=e("W8MJ"),Q=e.n(O),M=e("7W2i"),j=e.n(M),I=e("LQ03"),T=e.n(I),R=e("cDf5"),Z=e.n(R),x=e("q1tI"),k=e("TSYQ"),V=e.n(k),h=e("BGR+"),d=e("t23M"),g=e("H84U"),y=e("PEeC"),U=e("zT1h");function _(r){return r!==window?r.getBoundingClientRect():{top:0,bottom:window.innerHeight}}function $(r,o,i){if(i!==void 0&&o.top>r.top-i)return i+o.top}function q(r,o,i){if(i!==void 0&&o.bottom<r.bottom+i){var a=window.innerHeight-o.bottom;return i+a}}var J=["resize","scroll","touchstart","touchmove","touchend","pageshow","load"],p=[];function D(){return p}function Y(r,o){if(!!r){var i=p.find(function(a){return a.target===r});i?i.affixList.push(o):(i={target:r,affixList:[o],eventHandlers:{}},p.push(i),J.forEach(function(a){i.eventHandlers[a]=Object(U.a)(r,a,function(){i.affixList.forEach(function(s){s.lazyUpdatePosition()})})}))}}function b(r){var o=p.find(function(i){var a=i.affixList.some(function(s){return s===r});return a&&(i.affixList=i.affixList.filter(function(s){return s!==r})),a});o&&o.affixList.length===0&&(p=p.filter(function(i){return i!==o}),J.forEach(function(i){var a=o.eventHandlers[i];a&&a.remove&&a.remove()}))}var v=function(r,o,i,a){var s=arguments.length,u=s<3?o:a===null?a=Object.getOwnPropertyDescriptor(o,i):a,P;if((typeof Reflect=="undefined"?"undefined":Z()(Reflect))==="object"&&typeof Reflect.decorate=="function")u=Reflect.decorate(r,o,i,a);else for(var E=r.length-1;E>=0;E--)(P=r[E])&&(u=(s<3?P(u):s>3?P(o,i,u):P(o,i))||u);return s>3&&u&&Object.defineProperty(o,i,u),u};function l(){return typeof window!="undefined"?window:null}var c;(function(r){r[r.None=0]="None",r[r.Prepare=1]="Prepare"})(c||(c={}));var n=function(r){j()(i,r);var o=T()(i);function i(){var a;return S()(this,i),a=o.apply(this,arguments),a.state={status:c.None,lastAffix:!1,prevTarget:null},a.getOffsetTop=function(){var s=a.props.offsetBottom,u=a.props.offsetTop;return s===void 0&&u===void 0&&(u=0),u},a.getOffsetBottom=function(){return a.props.offsetBottom},a.savePlaceholderNode=function(s){a.placeholderNode=s},a.saveFixedNode=function(s){a.fixedNode=s},a.measure=function(){var s=a.state,u=s.status,P=s.lastAffix,E=a.props.onChange,L=a.getTargetFunc();if(!(u!==c.Prepare||!a.fixedNode||!a.placeholderNode||!L)){var H=a.getOffsetTop(),F=a.getOffsetBottom(),G=L();if(!!G){var K={status:c.None},z=_(G),w=_(a.placeholderNode),ee=$(w,z,H),X=q(w,z,F);ee!==void 0?(K.affixStyle={position:"fixed",top:ee,width:w.width,height:w.height},K.placeholderStyle={width:w.width,height:w.height}):X!==void 0&&(K.affixStyle={position:"fixed",bottom:X,width:w.width,height:w.height},K.placeholderStyle={width:w.width,height:w.height}),K.lastAffix=!!K.affixStyle,E&&P!==K.lastAffix&&E(K.lastAffix),a.setState(K)}}},a.prepareMeasure=function(){if(a.setState({status:c.Prepare,affixStyle:void 0,placeholderStyle:void 0}),!1)var s},a.render=function(){var s=a.context.getPrefixCls,u=a.state,P=u.affixStyle,E=u.placeholderStyle,L=a.props,H=L.prefixCls,F=L.children,G=V()(B()({},s("affix",H),P)),K=Object(h.a)(a.props,["prefixCls","offsetTop","offsetBottom","target","onChange"]);return x.createElement(d.a,{onResize:function(){a.updatePosition()}},x.createElement("div",A()({},K,{ref:a.savePlaceholderNode}),P&&x.createElement("div",{style:E,"aria-hidden":"true"}),x.createElement("div",{className:G,ref:a.saveFixedNode,style:P},x.createElement(d.a,{onResize:function(){a.updatePosition()}},F))))},a}return Q()(i,[{key:"getTargetFunc",value:function(){var s=this.context.getTargetContainer,u=this.props.target;return u!==void 0?u:s||l}},{key:"componentDidMount",value:function(){var s=this,u=this.getTargetFunc();u&&(this.timeout=setTimeout(function(){Y(u(),s),s.updatePosition()}))}},{key:"componentDidUpdate",value:function(s){var u=this.state.prevTarget,P=this.getTargetFunc(),E=null;P&&(E=P()||null),u!==E&&(b(this),E&&(Y(E,this),this.updatePosition()),this.setState({prevTarget:E})),(s.offsetTop!==this.props.offsetTop||s.offsetBottom!==this.props.offsetBottom)&&this.updatePosition(),this.measure()}},{key:"componentWillUnmount",value:function(){clearTimeout(this.timeout),b(this),this.updatePosition.cancel(),this.lazyUpdatePosition.cancel()}},{key:"updatePosition",value:function(){this.prepareMeasure()}},{key:"lazyUpdatePosition",value:function(){var s=this.getTargetFunc(),u=this.state.affixStyle;if(s&&u){var P=this.getOffsetTop(),E=this.getOffsetBottom(),L=s();if(L&&this.placeholderNode){var H=_(L),F=_(this.placeholderNode),G=$(F,H,P),K=q(F,H,E);if(G!==void 0&&u.top===G||K!==void 0&&u.bottom===K)return}}this.prepareMeasure()}}]),i}(x.Component);n.contextType=g.b,v([Object(y.b)()],n.prototype,"updatePosition",null),v([Object(y.b)()],n.prototype,"lazyUpdatePosition",null);var t=f.a=n},"YV/h":function(W,f,e){},ayqn:function(W,f,e){"use strict";Object.defineProperty(f,"__esModule",{value:!0});var N={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M872 474H286.9l350.2-304c5.6-4.9 2.2-14-5.2-14h-88.5c-3.9 0-7.6 1.4-10.5 3.9L155 487.8a31.96 31.96 0 000 48.3L535.1 866c1.5 1.3 3.3 2 5.2 2h91.5c7.4 0 10.8-9.2 5.2-14L286.9 550H872c4.4 0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z"}}]},name:"arrow-left",theme:"outlined"};f.default=N},bE4q:function(W,f,e){"use strict";var N=e("pVnL"),A=e.n(N),C=e("lSNA"),B=e.n(C),m=e("RIqP"),S=e.n(m),O=e("q1tI"),Q=e("TSYQ"),M=e.n(Q),j=e("Zm9Q"),I=e("HQEm"),T=e.n(I),R=e("XBQK"),Z=e("H84U"),x=function(v,l){var c={};for(var n in v)Object.prototype.hasOwnProperty.call(v,n)&&l.indexOf(n)<0&&(c[n]=v[n]);if(v!=null&&typeof Object.getOwnPropertySymbols=="function")for(var t=0,n=Object.getOwnPropertySymbols(v);t<n.length;t++)l.indexOf(n[t])<0&&Object.prototype.propertyIsEnumerable.call(v,n[t])&&(c[n[t]]=v[n[t]]);return c},k=function(l){var c=l.prefixCls,n=l.separator,t=n===void 0?"/":n,r=l.children,o=l.overlay,i=l.dropdownProps,a=x(l,["prefixCls","separator","children","overlay","dropdownProps"]),s=O.useContext(Z.b),u=s.getPrefixCls,P=u("breadcrumb",c),E=function(F){return o?O.createElement(R.a,A()({overlay:o,placement:"bottomCenter"},i),O.createElement("span",{className:"".concat(P,"-overlay-link")},F,O.createElement(T.a,null))):F},L;return"href"in a?L=O.createElement("a",A()({className:"".concat(P,"-link")},a),r):L=O.createElement("span",A()({className:"".concat(P,"-link")},a),r),L=E(L),r?O.createElement("span",null,L,t&&t!==""&&O.createElement("span",{className:"".concat(P,"-separator")},t)):null};k.__ANT_BREADCRUMB_ITEM=!0;var V=k,h=function(l){var c=l.children,n=O.useContext(Z.b),t=n.getPrefixCls,r=t("breadcrumb");return O.createElement("span",{className:"".concat(r,"-separator")},c||"/")};h.__ANT_BREADCRUMB_SEPARATOR=!0;var d=h,g=e("BvKs"),y=e("uaoM"),U=e("0n0R"),_=function(v,l){var c={};for(var n in v)Object.prototype.hasOwnProperty.call(v,n)&&l.indexOf(n)<0&&(c[n]=v[n]);if(v!=null&&typeof Object.getOwnPropertySymbols=="function")for(var t=0,n=Object.getOwnPropertySymbols(v);t<n.length;t++)l.indexOf(n[t])<0&&Object.prototype.propertyIsEnumerable.call(v,n[t])&&(c[n[t]]=v[n[t]]);return c};function $(v,l){if(!v.breadcrumbName)return null;var c=Object.keys(l).join("|"),n=v.breadcrumbName.replace(new RegExp(":(".concat(c,")"),"g"),function(t,r){return l[r]||t});return n}function q(v,l,c,n){var t=c.indexOf(v)===c.length-1,r=$(v,l);return t?O.createElement("span",null,r):O.createElement("a",{href:"#/".concat(n.join("/"))},r)}var J=function(l,c){return l=(l||"").replace(/^\//,""),Object.keys(c).forEach(function(n){l=l.replace(":".concat(n),c[n])}),l},p=function(l){var c=arguments.length>1&&arguments[1]!==void 0?arguments[1]:"",n=arguments.length>2?arguments[2]:void 0,t=S()(l),r=J(c,n);return r&&t.push(r),t},D=function(l){var c=l.prefixCls,n=l.separator,t=n===void 0?"/":n,r=l.style,o=l.className,i=l.routes,a=l.children,s=l.itemRender,u=s===void 0?q:s,P=l.params,E=P===void 0?{}:P,L=_(l,["prefixCls","separator","style","className","routes","children","itemRender","params"]),H=O.useContext(Z.b),F=H.getPrefixCls,G=H.direction,K,z=F("breadcrumb",c);if(i&&i.length>0){var w=[];K=i.map(function(X){var te=J(X.path,E);te&&w.push(te);var ne;return X.children&&X.children.length&&(ne=O.createElement(g.a,null,X.children.map(function(re){return O.createElement(g.a.Item,{key:re.path||re.breadcrumbName},u(re,E,i,p(w,re.path,E)))}))),O.createElement(V,{overlay:ne,separator:t,key:te||X.breadcrumbName},u(X,E,i,w))})}else a&&(K=Object(j.a)(a).map(function(X,te){return X&&(Object(y.a)(X.type&&(X.type.__ANT_BREADCRUMB_ITEM===!0||X.type.__ANT_BREADCRUMB_SEPARATOR===!0),"Breadcrumb","Only accepts Breadcrumb.Item and Breadcrumb.Separator as it's children"),Object(U.a)(X,{separator:t,key:te}))}));var ee=M()(z,B()({},"".concat(z,"-rtl"),G==="rtl"),o);return O.createElement("div",A()({className:ee,style:r},L),K)};D.Item=V,D.Separator=d;var Y=D,b=f.a=Y},bf48:function(W,f,e){"use strict";var N=e("lSNA"),A=e.n(N),C=e("J4zp"),B=e.n(C),m=e("q1tI"),S=e.n(m),O=e("TSYQ"),Q=e.n(O),M=e("h4NZ"),j=e.n(M),I=e("/hEp"),T=e.n(I),R=e("t23M"),Z=e("H84U"),x=e("bE4q"),k=e("Tckk"),V=e("gDlH"),h=e("YMnH"),d=function(p,D,Y){return!D||!Y?null:m.createElement(h.a,{componentName:"PageHeader"},function(b){var v=b.back;return m.createElement("div",{className:"".concat(p,"-back")},m.createElement(V.a,{onClick:function(c){Y&&Y(c)},className:"".concat(p,"-back-button"),"aria-label":v},D))})},g=function(p){return m.createElement(x.a,p)},y=function(p){var D=arguments.length>1&&arguments[1]!==void 0?arguments[1]:"ltr";return p.backIcon!==void 0?p.backIcon:D==="rtl"?m.createElement(T.a,null):m.createElement(j.a,null)},U=function(p,D){var Y=arguments.length>2&&arguments[2]!==void 0?arguments[2]:"ltr",b=D.title,v=D.avatar,l=D.subTitle,c=D.tags,n=D.extra,t=D.onBack,r="".concat(p,"-heading"),o=b||l||c||n;if(!o)return null;var i=y(D,Y),a=d(p,i,t),s=a||v||o;return m.createElement("div",{className:r},s&&m.createElement("div",{className:"".concat(r,"-left")},a,v&&m.createElement(k.a,v),b&&m.createElement("span",{className:"".concat(r,"-title"),title:typeof b=="string"?b:void 0},b),l&&m.createElement("span",{className:"".concat(r,"-sub-title"),title:typeof l=="string"?l:void 0},l),c&&m.createElement("span",{className:"".concat(r,"-tags")},c)),n&&m.createElement("span",{className:"".concat(r,"-extra")},n))},_=function(p,D){return D?m.createElement("div",{className:"".concat(p,"-footer")},D):null},$=function(p,D){return m.createElement("div",{className:"".concat(p,"-content")},D)},q=function(p){var D=m.useState(!1),Y=B()(D,2),b=Y[0],v=Y[1],l=function(n){var t=n.width;v(t<768)};return m.createElement(Z.a,null,function(c){var n,t=c.getPrefixCls,r=c.pageHeader,o=c.direction,i=p.prefixCls,a=p.style,s=p.footer,u=p.children,P=p.breadcrumb,E=p.className,L=!0;"ghost"in p?L=p.ghost:r&&"ghost"in r&&(L=r.ghost);var H=t("page-header",i),F=P&&P.routes?g(P):null,G=Q()(H,E,(n={"has-breadcrumb":F,"has-footer":s},A()(n,"".concat(H,"-ghost"),L),A()(n,"".concat(H,"-rtl"),o==="rtl"),A()(n,"".concat(H,"-compact"),b),n));return m.createElement(R.a,{onResize:l},m.createElement("div",{className:G,style:a},F,U(H,p,o),u&&$(H,u),_(H,s)))})};f.a=q},gDlH:function(W,f,e){"use strict";var N=e("pVnL"),A=e.n(N),C=e("q1tI"),B=e.n(C),m=e("4IlW"),S=function(M,j){var I={};for(var T in M)Object.prototype.hasOwnProperty.call(M,T)&&j.indexOf(T)<0&&(I[T]=M[T]);if(M!=null&&typeof Object.getOwnPropertySymbols=="function")for(var R=0,T=Object.getOwnPropertySymbols(M);R<T.length;R++)j.indexOf(T[R])<0&&Object.prototype.propertyIsEnumerable.call(M,T[R])&&(I[T[R]]=M[T[R]]);return I},O={border:0,background:"transparent",padding:0,lineHeight:"inherit",display:"inline-block"},Q=C.forwardRef(function(M,j){var I=function(d){var g=d.keyCode;g===m.a.ENTER&&d.preventDefault()},T=function(d){var g=d.keyCode,y=M.onClick;g===m.a.ENTER&&y&&y()},R=M.style,Z=M.noStyle,x=M.disabled,k=S(M,["style","noStyle","disabled"]),V={};return Z||(V=A()({},O)),x&&(V.pointerEvents="none"),V=A()(A()({},V),R),C.createElement("div",A()({role:"button",tabIndex:0,ref:j},k,{onKeyDown:I,onKeyUp:T,style:V}))});f.a=Q},h4NZ:function(W,f,e){"use strict";Object.defineProperty(f,"__esModule",{value:!0}),f.default=void 0;var N=A(e("jw4T"));function A(B){return B&&B.__esModule?B:{default:B}}var C=N;f.default=C,W.exports=C},jRje:function(W,f,e){"use strict";var N=e("GNNt"),A=e("wEI+"),C=e("q1tI"),B=e.n(C),m=e("TSYQ"),S=e.n(m),O=e("BGR+"),Q=e("rsCp"),M=e.n(Q),j=e("jYQm");function I(){return I=Object.assign||function(h){for(var d=1;d<arguments.length;d++){var g=arguments[d];for(var y in g)Object.prototype.hasOwnProperty.call(g,y)&&(h[y]=g[y])}return h},I.apply(this,arguments)}function T(h,d){var g=Object.keys(h);if(Object.getOwnPropertySymbols){var y=Object.getOwnPropertySymbols(h);d&&(y=y.filter(function(U){return Object.getOwnPropertyDescriptor(h,U).enumerable})),g.push.apply(g,y)}return g}function R(h){for(var d=1;d<arguments.length;d++){var g=arguments[d]!=null?arguments[d]:{};d%2?T(Object(g),!0).forEach(function(y){Z(h,y,g[y])}):Object.getOwnPropertyDescriptors?Object.defineProperties(h,Object.getOwnPropertyDescriptors(g)):T(Object(g)).forEach(function(y){Object.defineProperty(h,y,Object.getOwnPropertyDescriptor(g,y))})}return h}function Z(h,d,g){return d in h?Object.defineProperty(h,d,{value:g,enumerable:!0,configurable:!0,writable:!0}):h[d]=g,h}function x(h,d){if(h==null)return{};var g=k(h,d),y,U;if(Object.getOwnPropertySymbols){var _=Object.getOwnPropertySymbols(h);for(U=0;U<_.length;U++)(y=_[U],!(d.indexOf(y)>=0))&&(!Object.prototype.propertyIsEnumerable.call(h,y)||(g[y]=h[y]))}return g}function k(h,d){if(h==null)return{};var g={},y=Object.keys(h),U,_;for(_=0;_<y.length;_++)(U=y[_],!(d.indexOf(U)>=0))&&(g[U]=h[U]);return g}var V=function(d){var g=d.children,y=d.className,U=d.extra,_=d.style,$=d.renderContent,q=x(d,["children","className","extra","style","renderContent"]),J=Object(C.useContext)(A.b.ConfigContext),p=J.getPrefixCls,D=d.prefixCls||p("pro"),Y="".concat(D,"-footer-bar"),b=Object(C.useContext)(j.a),v=Object(C.useMemo)(function(){var c=b.hasSiderMenu,n=b.isMobile,t=b.siderWidth;if(!!c)return t?n?"100%":"calc(100% - ".concat(t,"px)"):"100%"},[b.collapsed,b.hasSiderMenu,b.isMobile,b.siderWidth]),l=B.a.createElement(B.a.Fragment,null,B.a.createElement("div",{className:"".concat(Y,"-left")},U),B.a.createElement("div",{className:"".concat(Y,"-right")},g));return Object(C.useEffect)(function(){return!b||!(b==null?void 0:b.setHasFooterToolbar)?function(){}:(b==null||b.setHasFooterToolbar(!0),function(){var c;b==null||((c=b.setHasFooterToolbar)===null||c===void 0)||c.call(b,!1)})},[]),B.a.createElement("div",I({className:S()(y,"".concat(Y)),style:R({width:v},_)},Object(O.a)(q,["prefixCls"])),$?$(R(R(R({},d),b),{},{leftWidth:v}),l):l)};f.a=V},jYQm:function(W,f,e){"use strict";var N=e("q1tI"),A=e.n(N),C=Object(N.createContext)({});f.a=C},jw4T:function(W,f,e){"use strict";var N=e("TqRt"),A=e("284h");Object.defineProperty(f,"__esModule",{value:!0}),f.default=void 0;var C=A(e("q1tI")),B=N(e("ayqn")),m=N(e("KQxl")),S=function(M,j){return C.createElement(m.default,Object.assign({},M,{ref:j,icon:B.default}))};S.displayName="ArrowLeftOutlined";var O=C.forwardRef(S);f.default=O},r4ZK:function(W,f,e){"use strict";Object.defineProperty(f,"__esModule",{value:!0});var N={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M869 487.8L491.2 159.9c-2.9-2.5-6.6-3.9-10.5-3.9h-88.5c-7.4 0-10.8 9.2-5.2 14l350.2 304H152c-4.4 0-8 3.6-8 8v60c0 4.4 3.6 8 8 8h585.1L386.9 854c-5.6 4.9-2.2 14 5.2 14h91.5c1.9 0 3.8-.7 5.2-2L869 536.2a32.07 32.07 0 000-48.4z"}}]},name:"arrow-right",theme:"outlined"};f.default=N},rsCp:function(W,f,e){},tMyG:function(W,f,e){"use strict";var N=e("cIOH"),A=e("15/o"),C=e("VNzZ"),B=e("GNNt"),m=e("wEI+"),S=e("YV/h"),O=e("AOa7"),Q=e("lUTK"),M=e("qVdP"),j=e("Telt"),I=e("bf48"),T=e("Znn+"),R=e("ZTPi"),Z=e("q1tI"),x=e.n(Z),k=e("TSYQ"),V=e.n(k),h=e("jYQm"),d=e("FRQA"),g=e("jRje"),y=e("u/V1"),U=e("95SA");function _(n,t){var r=Object.keys(n);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(n);t&&(o=o.filter(function(i){return Object.getOwnPropertyDescriptor(n,i).enumerable})),r.push.apply(r,o)}return r}function $(n){for(var t=1;t<arguments.length;t++){var r=arguments[t]!=null?arguments[t]:{};t%2?_(Object(r),!0).forEach(function(o){q(n,o,r[o])}):Object.getOwnPropertyDescriptors?Object.defineProperties(n,Object.getOwnPropertyDescriptors(r)):_(Object(r)).forEach(function(o){Object.defineProperty(n,o,Object.getOwnPropertyDescriptor(r,o))})}return n}function q(n,t,r){return t in n?Object.defineProperty(n,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):n[t]=r,n}function J(n,t){if(n==null)return{};var r=p(n,t),o,i;if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(n);for(i=0;i<a.length;i++)(o=a[i],!(t.indexOf(o)>=0))&&(!Object.prototype.propertyIsEnumerable.call(n,o)||(r[o]=n[o]))}return r}function p(n,t){if(n==null)return{};var r={},o=Object.keys(n),i,a;for(a=0;a<o.length;a++)(i=o[a],!(t.indexOf(i)>=0))&&(r[i]=n[i]);return r}function D(){return D=Object.assign||function(n){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var o in r)Object.prototype.hasOwnProperty.call(r,o)&&(n[o]=r[o])}return n},D.apply(this,arguments)}var Y=function(t){var r=t.tabList,o=t.tabActiveKey,i=t.onTabChange,a=t.tabBarExtraContent,s=t.tabProps,u=t.prefixedClassName;return r&&r.length?x.a.createElement(R.a,D({className:"".concat(u,"-tabs"),activeKey:o,onChange:function(E){i&&i(E)},tabBarExtraContent:a},s),r.map(function(P,E){return x.a.createElement(R.a.TabPane,D({},P,{tab:P.tab,key:P.key||E}))})):null},b=function(t,r,o){return!t&&!r?null:x.a.createElement("div",{className:"".concat(o,"-detail")},x.a.createElement("div",{className:"".concat(o,"-main")},x.a.createElement("div",{className:"".concat(o,"-row")},t&&x.a.createElement("div",{className:"".concat(o,"-content")},t),r&&x.a.createElement("div",{className:"".concat(o,"-extraContent")},r))))},v=function(t,r){var o,i,a,s=t.title,u=t.content,P=t.pageHeaderRender,E=t.header,L=t.extraContent,H=t.style,F=t.prefixCls,G=J(t,["title","content","pageHeaderRender","header","extraContent","style","prefixCls"]);if(P===!1)return null;if(P)return P($($({},t),r));var K=s;!s&&s!==!1&&(K=r.title);var z=$($($({},r),{},{title:K},G),{},{footer:Y($($({},G),{},{prefixedClassName:r.prefixedClassName}))},E);return!z.title&&!z.subTitle&&!((o=z.breadcrumb)===null||o===void 0?void 0:o.itemRender)&&!((i=z.breadcrumb)===null||i===void 0||((a=i.routes)===null||a===void 0)?void 0:a.length)&&!z.extra&&!z.tags&&!z.footer&&!z.avatar&&!z.backIcon?null:x.a.createElement(I.a,D({},z,{prefixCls:F}),(E==null?void 0:E.children)||b(u,L,r.prefixedClassName))},l=function(t){var r=t.children,o=t.loading,i=t.style,a=t.footer,s=t.affixProps,u=t.ghost,P=t.fixedHeader,E=Object(Z.useContext)(h.a),L=Object(Z.useContext)(m.b.ConfigContext),H=L.getPrefixCls,F=t.prefixCls||H("pro"),G="".concat(F,"-page-container"),K=V()(G,t.className,q({},"".concat(F,"-page-container-ghost"),u)),z=r?x.a.createElement("div",null,x.a.createElement("div",{className:"".concat(G,"-children-content")},r),E.hasFooterToolbar&&x.a.createElement("div",{style:{height:48,marginTop:24}})):null,w=v(t,$($({},E),{},{prefixCls:void 0,prefixedClassName:G})),ee=w?x.a.createElement("div",{className:"".concat(G,"-warp")},w):null;return x.a.createElement("div",{style:i,className:K},P&&ee?x.a.createElement(C.a,D({offsetTop:E.hasHeader&&E.fixedHeader?E.headerHeight:0},s),ee):ee,x.a.createElement(d.a,null,o?x.a.createElement(U.a,null):z),a&&x.a.createElement(g.a,{prefixCls:F},a))},c=f.a=l},"u/V1":function(W,f,e){}}]);
