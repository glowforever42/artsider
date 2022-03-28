import Profile from '@/views/account/profile/Profile'
import ProfileMyContents from '@/views/account/profile/ProfileMyContents'
import ProfileMyReviews from '@/views/account/profile/ProfileMyReviews'
import ProfileCalendar from '@/views/account/profile/ProfileCalendar'

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