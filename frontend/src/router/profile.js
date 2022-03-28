// import Vue from 'vue'
// import VueRouter from 'vue-router'

import Profile from '@/views/account/profile/Profile.vue'
import ProfileMyContents from '@/views/account/profile/ProfileMyContents.vue'
import ProfileMyReviews from '@/views/account/profile/ProfileMyReviews.vue'
import ProfileCalendar from '@/views/account/profile/ProfileCalendar.vue'


// Vue.use(VueRouter)

// export const router = new VueRouter({
//   routes: [
//     {
//       path: '/profile',
//       name: 'Profile',
//       component: Profile,
//       children: [
//         {
//           path: 'my-contents',
//           name: 'ProfileMyContents',
//           conmponent: ProfileMyContents
//         },
//         {
//           path: 'my-reviews',
//           name: 'ProfileMyReviews',
//           component: ProfileMyReviews
//         }, 
//         {
//           path: 'calendar',
//           name: 'ProfileCalendar',
//           component: ProfileCalendar
//         }
//       ],
//   },
//   ]
// })

export default [
  {
      path: '/profile',
      name: 'Profile',
      component: Profile,
      children: [
        {
          path: 'my-contents',
          name: 'ProfileMyContents',
          conmponent: ProfileMyContents
        },
        {
          path: 'my-reviews',
          name: 'ProfileMyReviews',
          component: ProfileMyReviews
        }, 
        {
          path: 'calendar',
          name: 'ProfileCalendar',
          component: ProfileCalendar
        }
      ],
  },
]