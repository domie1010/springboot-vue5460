<template>
<view class="content">
	<view :style='{"minHeight":"100vh","padding":"0","overflow":"hidden","alignItems":"flex-start","flexWrap":"wrap","background":"#f0f8fa","flexDirection":"row","display":"flex","width":"100%","height":"auto"}'>
		<view class="list-swiper-4" :style='{"width":"100%","margin":"0 auto","position":"relative","background":"none","height":"600rpx"}' @touchstart="touchStart" @touchmove="touchMove" @touchend="touchEnd">
			<view :style='{"overflow":"hidden","top":"0%","left":"0%","background":"none","width":"100%","position":"absolute","height":"100%"}' class="item animate__animated" :class="prevNumList4 == index  ? 'animate__' : (numList4 == index  ? 'animate__' : '')" v-for="(swiper,index) in swiperList" :key="index" v-if="numList4 == index || prevNumList4 == index">
				<image :style='{"width":"calc(100% - 0px)","margin":"auto","objectFit":"cover","borderRadius":"8rpx 8rpx 20rpx 20rpx","display":"block","height":"100%"}' mode="aspectFill" :src="baseUrl+swiper.img" @tap="onSwiperTap(swiper)"></image>
				<view :style='{"padding":"8rpx 20rpx 80rpx","margin":"-20rpx 0 0 0","color":"#fff","textAlign":"center","display":"none","transform":"translate3d(-50%, -50%, 0)","top":"100%","left":"50%","background":"rgba(255, 255, 255, 0.3)","width":"100%","lineHeight":"1.5","fontSize":"28rpx","position":"absolute"}'>{{ swiper.title }}</view>
			</view>
			<view class="animate__navigation" :style='{"alignItems":"center","left":"0%","bottom":"2%","background":"rgba(0,0,0,.0)","display":"none","width":"100%","position":"absolute","justifyContent":"center","height":"40rpx"}'>
				<block v-for="(swiper,index) in swiperList" :key="index">
					<text class="navigation-item" v-if="numList4 == index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#4ab030","height":"16rpx"}'></text>
					<text class="navigation-item" v-if="numList4 != index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#fff","height":"16rpx"}'></text>
				</block>
			</view>
		</view>
		<!-- menu -->
        <view style="width: 100%" v-if="swiperMenuList.length">
            <swiper :style='{"padding":"0 0 12rpx","boxShadow":"0 4rpx 0px rgba(0,0,0,.1)","margin":"20rpx auto","borderRadius":"8rpx","flexWrap":"wrap","background":"#fff","display":"flex","width":"calc(100% - 48rpx)","justifyContent":"space-between","order":"2"}' class="swiper">
                <swiper-item v-for="(swiper,index) in (new Array(Number(Math.ceil(swiperMenuList.length / (rows * column)))).fill('').map((val, i) => i+1))" :key="index">
                    <view v-for="(row, index1) in (new Array(Number(rows)).fill('').map((val, i) => i+1))" :key="index1" style="display: flex;flex-wrap: wrap;">
                        <view v-for="(col, index2) in (new Array(Number(column)).fill('').map((val, i) => i+1))" :key="index2" v-if="(((row+rows*(swiper-1) -1)*column + col -1)<swiperMenuList.length)" :style='{"width":"25%","padding":"12rpx 0","margin":"10rpx 0","height":"auto"}' class="menu-list">
                            <view :class="swiperMenuList[(row+rows*(swiper-1) -1)*column + col -1].child[0].appFrontIcon" @tap="onPageTap2('../'+swiperMenuList[(row+rows*(swiper-1) -1)*column + col -1].child[0].tableName+'/list')" :style='{"padding":"0px","margin":"0px auto","color":"#fff","borderRadius":"100%","textAlign":"center","background":"#ffcc00","display":"block","width":"88rpx","lineHeight":"88rpx","fontSize":"64rpx","height":"88rpx"}'>
                            </view>
                            <view :style='{"padding":"0","margin":"12rpx auto 0","color":"#333","textAlign":"center","width":"100%","lineHeight":"28rpx","fontSize":"28rpx"}'>{{swiperMenuList[(row+rows*(swiper-1) -1)*column + col -1].child[0].menu.split("列表")[0]}} </view>
                        </view>
                    </view>
                </swiper-item>
            </swiper>
        </view>
		<!-- menu -->
		<!-- 商品推荐 -->
		<view class="listBox recommend" :style='{"width":"calc(100% - 48rpx)","padding":"0 0 10rpx","margin":"20rpx auto","borderRadius":"0 0 8rpx 8rpx","background":"#fff","order":"3"}'>
			<view class="title" :style='{"padding":"0 24rpx","margin":"0","borderColor":"#ffcc00","borderRadius":"8rpx 8rpx 0 0","textAlign":"center","background":"#fdf6ec","borderWidth":"6rpx 0 0","width":"100%","lineHeight":"80rpx","borderStyle":"solid","height":"auto"}'>
				<view :style='{"color":"#333","fontSize":"32rpx","fontWeight":"600"}'>课程信息推荐</view>
			</view>
			<!-- 样式3 -->
			<view class="list-box style3" :style='{"width":"100%","padding":"10rpx 0 0","margin":"0","height":"auto"}'>
				<view @tap="onDetailTap('kechengxinxi',product.id)" v-for="(product,index) in kechengxinxilist" :key="index" class="list-item" :style='{"padding":"20rpx","margin":"0 0 0px","borderRadius":"0px","alignItems":"flex-start","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","height":"auto"}'>
					<image :style='{"width":"200rpx","objectFit":"cover","display":"block","height":"260rpx"}' class="list-item-image" mode="aspectFill" v-if="product.kechengtupian.substring(0,4)=='http'" :src="product.kechengtupian"></image>
					<image :style='{"width":"200rpx","objectFit":"cover","display":"block","height":"260rpx"}' class="list-item-image" mode="aspectFill" v-else :src="product.kechengtupian?baseUrl+product.kechengtupian.split(',')[0]:''"></image>
					<view class="list-item-body" :style='{"minHeight":"240rpx","padding":"0 0 0 20rpx","margin":"0","alignItems":"center","flexWrap":"wrap","background":"url(http://codegen.caihongy.cn/20240221/8e2fddf1cb4644f6b32d79a4137c914d.png) no-repeat right bottom","display":"flex","width":"calc(100% - 200rpx)","position":"relative","height":"auto"}'>
						<view :style='{"padding":"0 0px","margin":"0","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"40rpx","fontSize":"24rpx","textOverflow":"ellipsis"}' class="list-item-title">课程名称:{{product.kechengmingcheng}}</view>
						<view :style='{"width":"100%","padding":"0 0px","order":"6"}'>
						  <text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
						  <text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.addtime}}</text>
						</view>
						<view :style='{"padding":"0 0px","margin":"0 10rpx 0 0","display":"inline-block"}'>
						  <text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
						  <text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.thumbsupnum}}</text>
						</view>
						<view :style='{"padding":"0 0px","margin":"0 10rpx 0 0","display":"inline-block"}'>
						  <text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
						  <text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.storeupnum}}</text>
						</view>
					</view>
				</view>
			</view>
		</view>
		<!-- 商品推荐 -->
		
		<!-- 商品列表 -->
		<!-- 商品列表 -->
		<!-- 新闻资讯 -->
		<view class="listBox news" :style='{"width":"calc(100% - 48rpx)","padding":"0","margin":"40rpx auto","borderRadius":"8rpx","background":"#fff","order":"6"}'>
			<view class="title" :style='{"padding":"0 20rpx","margin":"0 auto","borderColor":"#ffcc00","textAlign":"center","display":"block","justifyContent":"space-between","borderRadius":"8rpx 8rpx 0 0","background":"#fdf6ec","borderWidth":"6rpx 0 0","width":"calc(100% - 0px)","lineHeight":"80rpx","position":"relative","borderStyle":"solid"}'>
				<view :style='{"color":"#333","fontSize":"32rpx","fontWeight":"600"}'>通知公告</view>
				<view :style='{"position":"absolute","right":"20rpx","alignItems":"center","top":"0px","justifyContent":"center","display":"flex"}' @tap="onPageTap('news')">
				  <text :style='{"color":"#888","fontSize":"28rpx"}'>更多</text>
				  <text class="icon iconfont " :style='{"color":"#888","fontSize":"28rpx"}'></text>
				</view>
			</view>
		  <!-- 样式5 -->
		  <view class="news-box2" :style='{"width":"100%","padding":"24rpx","margin":"0","height":"auto"}'>
			<block v-for="(item,index) in news" :key="index">
			  <view @tap="onNewsDetailTap(item.id)" v-if="index==0" class="list-item" :style='{"width":"100%","padding":"0","margin":"0 0 20rpx","position":"relative","height":"auto"}'>
				<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"400rpx"}' mode="aspectFill" class="listmpic" :src="baseUrl+item.picture"></image>
				<view :style='{"width":"100%","padding":"10rpx 0","position":"absolute","left":"0","bottom":"0","background":"rgba(0,0,0,.3)"}'>
					<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.5","fontSize":"28rpx","textOverflow":"ellipsis","fontWeight":"600"}' class="title">{{item.title}}</view>
					<view :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","width":"100%","lineHeight":"1.5","fontSize":"28rpx","textOverflow":"ellipsis"}' class="text">{{item.introduction}}</view>
					<view :style='{"padding":"0 20rpx"}'>
					  <text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
					  <text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.addtime}}</text>
					</view>
					<view :style='{"padding":"0 20rpx"}'>
					  <text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
					  <text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.name}}</text>
					</view>
					<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
					  <text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
					  <text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.thumbsupnum}}</text>
					</view>
					<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
					  <text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
					  <text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.storeupnum}}</text>
					</view>
					<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
					  <text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
					  <text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.clicknum}}</text>
					</view>
				</view>
			  </view>
			  <view @tap="onNewsDetailTap(item.id)" v-if="index>0" class="list-item" :style='{"padding":"0 0 10rpx","borderColor":"#ccc","margin":"0 0 20rpx 0","borderWidth":"0 0 2rpx 0","background":"url(http://codegen.caihongy.cn/20240221/8e2fddf1cb4644f6b32d79a4137c914d.png) no-repeat right center","width":"100%","borderStyle":"solid","height":"auto"}'>
				<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"1.5","fontSize":"28rpx","textOverflow":"ellipsis","fontWeight":"600"}' class="title">{{item.title}}</view>
				<view :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","whiteSpace":"nowrap","color":"#666","width":"calc(100% - 48rpx)","lineHeight":"1.5","fontSize":"28rpx","textOverflow":"ellipsis"}' class="text">{{item.introduction}}</view>
				<view :style='{"padding":"0 20rpx"}'>
				  <text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
				  <text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.addtime}}</text>
				</view>
				<view :style='{"padding":"0 20rpx"}'>
				  <text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
				  <text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.name}}</text>
				</view>
				<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
				  <text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
				  <text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.thumbsupnum}}</text>
				</view>
				<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
				  <text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
				  <text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.storeupnum}}</text>
				</view>
				<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
				  <text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
				  <text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.clicknum}}</text>
				</view>
			  </view>
			</block>
		  </view>
		</view>
		<!-- 新闻资讯 -->
	</view>
</view>
</template>

<script>
    import menu from '@/utils/menu'
	import '@/assets/css/global-restaurant.css'
	import uniIcons from "@/components/uni-ui/lib/uni-icons/uni-icons.vue"
	export default {
		components: {
			uniIcons
		},
		data() {
			return {
				startX: 0,
				prevNumList4: '',
				numList4: 0,
				timerList4: null,
				flagList4: false,
				navigationActive: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#4ab030","height":"16rpx"},
				navigationDefault: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#fff","height":"16rpx"},
				options2: {
					effect: 'flip',
					loop : true
				},
				options3: {
					effect: 'cube',
					loop : true,
					cubeEffect: {
						shadow: true,
						slideShadows: true,
						shadowOffset: 20,
						shadowScale: 0.94,
					}
				},
				rows: 2,
				column: 4,
				iconArr: [
				  'cuIcon-same',
				  'cuIcon-deliver',
				  'cuIcon-evaluate',
				  'cuIcon-shop',
				  'cuIcon-ticket',
				  'cuIcon-cascades',
				  'cuIcon-discover',
				  'cuIcon-question',
				  'cuIcon-pic',
				  'cuIcon-filter',
				  'cuIcon-footprint',
				  'cuIcon-pulldown',
				  'cuIcon-pullup',
				  'cuIcon-moreandroid',
				  'cuIcon-refund',
				  'cuIcon-qrcode',
				  'cuIcon-remind',
				  'cuIcon-profile',
				  'cuIcon-home',
				  'cuIcon-message',
				  'cuIcon-link',
				  'cuIcon-lock',
				  'cuIcon-unlock',
				  'cuIcon-vip',
				  'cuIcon-weibo',
				  'cuIcon-activity',
				  'cuIcon-friendadd',
				  'cuIcon-friendfamous',
				  'cuIcon-friend',
				  'cuIcon-goods',
				  'cuIcon-selection'
				],
                role : '',
                menuList: [],
                swiperMenuList:[],
                user: {},
                tableName:'',

				//轮播
				swiperList: [],
				kechengxinxilist: [],
				news: [],
			}
		},
		watch: {
		},
		mounted() {
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
        async onLoad(){
            
        },
		async onShow() {
			this.swiperMenuList = []
			this.role = uni.getStorageSync("appRole");
			let table = uni.getStorageSync("nowTable");
			let res = await this.$api.session(table);
			this.user = res.data;
			this.tableName = table;
			let menus = menu.list();
			this.menuList = menus;
			this.menuList.forEach((item,key) => {
			    if(key==0) {
			        item.frontMenu.forEach((item2,key2) => {
			            if(item2.child[0].buttons.indexOf("查看")>-1) {
			                this.swiperMenuList.push(item2);
			            }
			        })
			    }
			})
            // let res;
			// 轮播图
			let swiperList = []
			res = await this.$api.list('config', {
				page: 1,
				limit: 5
			});
			for (let item of res.data.list) {
				if (item.name.indexOf('picture') >= 0 && item.value && item.value!="" && item.value!=null ) {
					swiperList.push({
						img: item.value,
                        title: item.name,
						url: item.url
					});
				}
			}
			if (swiperList) {
				this.swiperList = swiperList;
			}
			
			this.prevNumList4 = this.swiperList.length - 1
			this.timerList4 = setInterval(this.autoPlayList4, 6000)

			// 推荐信息
			this.getRecommendList()
			this.getHomeList()
			this.getNewsList()
		},
		methods: {
			uGetRect(selector, all) {
				return new Promise(resolve => {
					uni.createSelectorQuery()
					.in(this)
					[all ? 'selectAll' : 'select'](selector)
					.boundingClientRect(rect => {
						if (all && Array.isArray(rect) && rect.length) {
							resolve(rect);
						}
						if (!all && rect) {
							resolve(rect);
						}
					})
					.exec();
				});
			},
			cloneData(data) {
				return JSON.parse(JSON.stringify(data));
			},
			newsTabClick2(index){
				this.newsIndex2 = index
				this.getNewsList()
			},
			async getNewsList(){
				let res;
				let params = {
					page: 1,
					limit: 6,
					sort: 'id',
					order: 'desc',
				}
				// 通知公告
				res = await this.$api.list('news', params)
				this.news = res.data.list
			},
			homeTabClick2(index,name){
				this['home' + name + 'Index2'] = index
				this.getHomeList()
			},
			async getHomeList(){
				let res;
				let params;
			},
			recommendTabClick2(index,name){
				this[name + 'Index2'] = index
				this.getRecommendList()
			},
			async getRecommendList(){
				let res;
				let params;
				// 推荐信息
				params = {
					page: 1,
					limit: 12,
				}
				if(uni.getStorageSync("appUserid")) {
					res = await this.$api.recommend2('kechengxinxi', params);
				} else {
					res = await this.$api.recommend('kechengxinxi', params);
				}
				this.kechengxinxilist = res.data.list
				

			},
			autoPlayList4() {
				this.prevNumList4 = this.numList4
			
				this.numList4++
				if (this.numList4 == this.swiperList.length) this.numList4 = 0
			},
			touchStart(event) {
				this.startX = event.touches[0].clientX
				
				clearInterval(this.timerList4)
				this.flagList4 = true
			},
			touchMove(event) {
				const currentX = event.touches[0].clientX;
				const deltaX = currentX - this.startX;
				
				if (deltaX > 50) {
					// 向右滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4++
						if (this.numList4 == this.swiperList.length) this.numList4 = 0
					}
					
				} else if (deltaX < -50) {
					// 向左滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4--
						if (this.numList4 < 0) this.numList4 = this.swiperList.length - 1
					}
				}
			},
			touchEnd() {
				this.startX = 0
				this.timerList4 = setInterval(this.autoPlayList4, 6000)
				this.flagList4 = false
			},
			//轮播图跳转
			onSwiperTap(e) {
				if(e.url) {
					if (e.url.indexOf('https') != -1) {
						window.open(e.url)
					} else {
						this.$utils.jump(e.url)
					}
				}
			},
			// 新闻详情
			onNewsDetailTap(id) {
				this.$utils.jump(`../news-detail/news-detail?id=${id}`)
			},
			// 推荐列表点击详情
			onDetailTap(tableName, id) {
				this.$utils.jump(`../${tableName}/detail?id=${id}`)
			},
			onPageTap(tableName){

				uni.navigateTo({
					url: `../${tableName}/list`,
					fail: function(){
						uni.switchTab({
							url: `../${tableName}/list`
						});
					}
				});
				// this.$utils.jump(`../${tableName}/list`)
			},
            onPageTap2(url) {
                uni.setStorageSync("useridTag",0);
                uni.navigateTo({
                    url: url,
                    fail: function() {
                        uni.switchTab({
                            url: url
                        });
                    }
                });
            }
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.list-swiper-4 .animate__animated {
		--animate-delay: 300ms;
	}

</style>
